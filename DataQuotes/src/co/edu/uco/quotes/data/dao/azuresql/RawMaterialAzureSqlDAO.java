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
import co.edu.uco.quotes.data.dao.RawMaterialDAO;
import co.edu.uco.quotes.data.dao.connection.ConnectionSQL;
import co.edu.uco.quotes.dto.RawMaterialDTO;
import co.edu.uco.quotes.dto.RawMaterialTypeDTO;

public class RawMaterialAzureSqlDAO extends ConnectionSQL implements RawMaterialDAO {

	protected RawMaterialAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static RawMaterialDAO build(Connection connection) {
		return new RawMaterialAzureSqlDAO(connection);
	}

	@Override
	public void create(RawMaterialDTO rawMaterial) {
//		private int id;
//		private String name;
//		private String details;
//		private int value;
//		private RawMaterialTypeDTO rawMaterialType;
		
		String sql = "INSERT INTO RawMaterial (name, rawMaterialType, details, value) VALUES(?,?,?,?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, rawMaterial.getName());
			preparedStatement.setInt(2, rawMaterial.getRawMaterialType().getId());
			preparedStatement.setString(3, rawMaterial.getDetails());
			preparedStatement.setInt(4, rawMaterial.getValue());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to create a new RawMaterial registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to create a new RawMaterial registry on sql server", exception);

		}
		
	}

	@Override
	public void update(RawMaterialDTO rawMaterial) {
		String sql = "UPDATE RawMaterial SET name = ?, rawMaterialType=?, details=?, value=?  WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, rawMaterial.getName());
			preparedStatement.setInt(2, rawMaterial.getRawMaterialType().getId());
			preparedStatement.setString(3, rawMaterial.getDetails());
			preparedStatement.setInt(4, rawMaterial.getValue());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to update a new RawMaterial registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to update RawMaterial registry on sql server", exception);

		}
	}

	@Override
	public void delete(int id) {

		String sql = "DELETE FROM RawMaterial WHERE (id=?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1,id);
			
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to delete a RawMaterial registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to delete a RawMaterial registry on sql server", exception);

		
		}
		
	}
	
	@Override
	public List<RawMaterialDTO> find(RawMaterialDTO rawMaterial) {
	
		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<RawMaterialDTO> results = new ArrayList<RawMaterialDTO>();

		StringBuilder sb = new StringBuilder(SPACE);
		sb.append("Select id, name, rawMaterialType, details, value").append(SPACE);
		sb.append("From RawMaterial ");

		if (!UtilObject.getUtilObject().isNull(rawMaterial)) {

			if (UtilNumeric.getUtilNumeric().isGreatherThan(rawMaterial.getId(), 0)) {
				sb.append("WHERE").append(SPACE);
				sb.append("id = ? ");
				parameters.add(rawMaterial.getId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(rawMaterial.getName())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("name = ? ");
				parameters.add(UtilText.trim(rawMaterial.getName()));
				setWhere = false;
			}
			
			if (UtilNumeric.getUtilNumeric().isGreatherThan(rawMaterial.getRawMaterialType().getId(),0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("rawMaterialType = ? ");
				parameters.add(rawMaterial.getRawMaterialType().getId());
				setWhere = false;
			}			
			
		
			if (!UtilText.isEmpty(rawMaterial.getDetails())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("details = ? ");
				parameters.add(UtilText.trim(rawMaterial.getDetails()));
				setWhere = false;
			}
			
			if (UtilNumeric.getUtilNumeric().isGreatherThan(rawMaterial.getValue(),0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("value = ? ");
				parameters.add(rawMaterial.getValue());
				setWhere = false;
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
					"There was a problem trying to find rawMateriale registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to find an rawMaterial registry on sql server", exception);

		}

		return results;
		
	}
	
	private List<RawMaterialDTO> executeQuery(PreparedStatement preparedStatement) {

		List<RawMaterialDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recover rawMaterial registry on sql server",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to execute the query for recover rawMaterial registry on sql server",
					exception);

		}

		return results;

	}

	private List<RawMaterialDTO> assembleResults(ResultSet resultSet) {
		List<RawMaterialDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {

				results.add(assembleDTO(resultSet));

			}

		} catch (QuotesException exception) {

			throw exception;

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException("There was a problem trying to recover the rawMaterial",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to recover the rawMaterials registry on sql server", exception);

		}

		return results;

	}

	private RawMaterialDTO assembleDTO(ResultSet resultSet) {

		RawMaterialDTO dto = new RawMaterialDTO();

		try {
			
			RawMaterialTypeDTO rawMaterialType = new RawMaterialTypeDTO(resultSet.getInt("rawMaterialType"), SPACE);
			
			dto.setId(resultSet.getInt("id"));
			dto.setName(resultSet.getString("name"));
			dto.setRawMaterialType(rawMaterialType);
			dto.setDetails(resultSet.getString("details"));
			dto.setValue(resultSet.getInt("value"));


		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException("There was a problem trying to assemble the rawMaterialss",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to assemble the id rawMaterials on sql server", exception);

		}

		return dto;

	}

}
