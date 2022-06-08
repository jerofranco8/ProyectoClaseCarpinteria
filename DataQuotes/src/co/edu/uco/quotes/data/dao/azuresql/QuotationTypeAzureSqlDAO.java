package co.edu.uco.quotes.data.dao.azuresql;

import static co.edu.uco.crosscutting.util.text.UtilText.SPACE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;
import co.edu.uco.quotes.crosscutting.exception.QuotesException;
import co.edu.uco.quotes.data.dao.QuotationTypeDAO;
import co.edu.uco.quotes.data.dao.connection.ConnectionSQL;
import co.edu.uco.quotes.dto.QuotationTypeDTO;


public class QuotationTypeAzureSqlDAO extends ConnectionSQL implements QuotationTypeDAO {

	protected QuotationTypeAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static QuotationTypeDAO build(Connection connection) {
		return new QuotationTypeAzureSqlDAO(connection);
	}

	@Override
	public void create(QuotationTypeDTO quotationType) {

		
		String sql = "INSERT INTO QuotationType (name, cabinetMeterValue, datil) VALUES(?,?,?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, quotationType.getName());
			preparedStatement.setInt(2, quotationType.getCabinetMeterValue());
			preparedStatement.setString(3, quotationType.getDatil());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to create a new quotationType registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to create a new quotationType registry on sql server", exception);

		}
		
	}

	@Override
	public void update(QuotationTypeDTO quotationType) {
		String sql = "UPDATE QuotationType SET name = ?, cabinetMeterValue=?, datil=?  WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, quotationType.getName());
			preparedStatement.setInt(2, quotationType.getCabinetMeterValue());
			preparedStatement.setString(3, quotationType.getDatil());
			preparedStatement.setInt(4, quotationType.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to update a new quotationType registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to update quotationType registry on sql server", exception);

		}
		
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM QuotationType WHERE (id=?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1,id);
			
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to delete a quotationType registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to delete a quotationType registry on sql server", exception);

		}
	}
	

	
	@Override
	public List<QuotationTypeDTO> find(QuotationTypeDTO quotationType) {

		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<QuotationTypeDTO> results = new ArrayList<QuotationTypeDTO>();

		StringBuilder sb = new StringBuilder(SPACE);
		sb.append("Select id, name, cabinetMeterValue, datil").append(SPACE);
		sb.append("From QuotationType ");

		if (!UtilObject.getUtilObject().isNull(quotationType)) {

			if (UtilNumeric.getUtilNumeric().isGreatherThan(quotationType.getId(), 0)) {
				sb.append("WHERE").append(SPACE);
				sb.append("id = ? ");
				parameters.add(quotationType.getId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(quotationType.getName())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("name = ? ");
				parameters.add(UtilText.trim(quotationType.getName()));
				setWhere = false;
			}
			
			if (UtilNumeric.getUtilNumeric().isGreatherThan(quotationType.getCabinetMeterValue(),0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("cabinetMeterValue = ? ");
				parameters.add(quotationType.getCabinetMeterValue());
				setWhere = false;
			}			
			
		
			if (!UtilText.isEmpty(quotationType.getDatil())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("datil = ? ");
				parameters.add(UtilText.trim(quotationType.getDatil()));
				
			}

		}

		sb.append("ORDER BY name ASC");

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sb.toString())) {

			for (int index = 0; index < parameters.size(); index++) {
				preparedStatement.setObject(index + 1, parameters.get(index));
			}

			results = executeQuery(preparedStatement);

		} catch (QuotesException exception) {
			throw exception;

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to find quotationTypee registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to find an quotationType registry on sql server", exception);

		}

		return results;
		
	}
	
	private List<QuotationTypeDTO> executeQuery(PreparedStatement preparedStatement) {

		List<QuotationTypeDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recover quotationType registry on sql server",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to execute the query for recover quotationType registry on sql server",
					exception);

		}

		return results;

	}

	private List<QuotationTypeDTO> assembleResults(ResultSet resultSet) {
		List<QuotationTypeDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {

				results.add(assembleDTO(resultSet));

			}

		} catch (QuotesException exception) {

			throw exception;

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException("There was a problem trying to recover the quotationType",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to recover the quotationTypes registry on sql server", exception);

		}

		return results;

	}

	private QuotationTypeDTO assembleDTO(ResultSet resultSet) {

		QuotationTypeDTO dto = new QuotationTypeDTO();

		try {
			
			dto.setId(resultSet.getInt("id"));
			dto.setName(resultSet.getString("name"));
			dto.setCabinetMeterValue(resultSet.getInt("cabinetMeterValue"));
			dto.setDatil(resultSet.getString("datail"));
			
			

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException("There was a problem trying to assemble the quotationTypess",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to assemble the id quotationTypes on sql server", exception);

		}

		return dto;

	}


}
