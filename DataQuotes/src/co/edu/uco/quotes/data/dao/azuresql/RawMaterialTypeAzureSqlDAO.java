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
import co.edu.uco.quotes.data.dao.RawMaterialTypeDAO;
import co.edu.uco.quotes.data.dao.connection.ConnectionSQL;
import co.edu.uco.quotes.dto.RawMaterialTypeDTO;

public class RawMaterialTypeAzureSqlDAO extends ConnectionSQL implements RawMaterialTypeDAO {

	protected RawMaterialTypeAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static RawMaterialTypeDAO build(Connection connection) {
		return new RawMaterialTypeAzureSqlDAO(connection);
	}

	@Override
	public void create(RawMaterialTypeDTO rawMaterialType) {
		String sql = "INSERT INTO RawMaterialType (name) VALUES(?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, rawMaterialType.getName());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to create a new raw Material Type registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to create a new raw Material Type registry on sql server", exception);

		}

	}

	@Override
	public void update(RawMaterialTypeDTO rawMaterialType) {

		String sql = "UPDATE RawMaterialType SET name = ? WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, rawMaterialType.getName());
			preparedStatement.setInt(2, rawMaterialType.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to update a new raw Material Type registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to update raw Material Type registry on sql server", exception);

		}

	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM RawMaterialType WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to delete an raw Material Type registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to delete an raw Material Type registry on sql server", exception);

		}

	}



	@Override
	public List<RawMaterialTypeDTO> find(RawMaterialTypeDTO rawMaterialType) {

		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<RawMaterialTypeDTO> results = new ArrayList<RawMaterialTypeDTO>();

		StringBuilder sb = new StringBuilder(SPACE);
		sb.append("Select id, name").append(SPACE);
		sb.append("From RawMaterialType ");

		if (!UtilObject.getUtilObject().isNull(rawMaterialType)) {

			if (UtilNumeric.getUtilNumeric().isGreatherThan(rawMaterialType.getId(), 0)) {
				sb.append("WHERE").append(SPACE);
				sb.append("id = ? ");
				parameters.add(rawMaterialType.getId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(rawMaterialType.getName())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("name = ? ");
				parameters.add(UtilText.trim(rawMaterialType.getName()));
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
					"There was a problem trying to find raw Material Type registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to find an raw Material Type registry on sql server", exception);

		}

		return results;

	}
	

	private List<RawMaterialTypeDTO> executeQuery(PreparedStatement preparedStatement) {

		List<RawMaterialTypeDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recover raw Material Type registry on sql server",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to execute the query for recover raw Material Type registry on sql server",
					exception);

		}

		return results;

	}

	private List<RawMaterialTypeDTO> assembleResults(ResultSet resultSet) {
		List<RawMaterialTypeDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {

				results.add(assembleDTO(resultSet));

			}

		} catch (QuotesException exception) {

			throw exception;

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException("There was a problem trying to recover the raw Material Types",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to recover the raw Material Types registry on sql server", exception);

		}

		return results;

	}

	private RawMaterialTypeDTO assembleDTO(ResultSet resultSet) {

		RawMaterialTypeDTO dto = new RawMaterialTypeDTO();

		try {

			dto.setId(resultSet.getInt("id"));
			dto.setName(resultSet.getString("name"));

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException("There was a problem trying to assemble the raw Material Types",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to assemble the raw Material Types on sql server", exception);

		}

		return dto;

	}

	


}
