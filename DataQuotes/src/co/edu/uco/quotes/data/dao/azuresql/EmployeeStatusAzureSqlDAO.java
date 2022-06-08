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
import co.edu.uco.quotes.data.dao.EmployeeStatusDAO;
import co.edu.uco.quotes.data.dao.connection.ConnectionSQL;
import co.edu.uco.quotes.dto.EmployeeStatusDTO;

public class EmployeeStatusAzureSqlDAO extends ConnectionSQL implements EmployeeStatusDAO {

	protected EmployeeStatusAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static EmployeeStatusDAO build(Connection connection) {
		return new EmployeeStatusAzureSqlDAO(connection);
	}

	@Override
	public void create(EmployeeStatusDTO employeeStatus) {
		String sql = "INSERT INTO EmployeeStatus (name) VALUES(?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, employeeStatus.getName());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to create a new EmployeeStatus registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to create a new EmployeeStatus registry on sql server", exception);

		}

		
	}

	@Override
	public void update(EmployeeStatusDTO employeeStatus) {
		String sql = "UPDATE EmployeeStatus SET name = ? WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, employeeStatus.getName());
			preparedStatement.setInt(2, employeeStatus.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to update a new EmployeeStatus registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to update EmployeeStatus registry on sql server", exception);

		}
		
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM EmployeeStatus WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to delete an EmployeeStatus registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to delete an EmployeeStatus registry on sql server", exception);

		}
		
	}

	@Override
	public List<EmployeeStatusDTO> find(EmployeeStatusDTO employeeStatus) {

		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<EmployeeStatusDTO> results = new ArrayList<EmployeeStatusDTO>();

		StringBuilder sb = new StringBuilder(SPACE);
		sb.append("Select id, name").append(SPACE);
		sb.append("From EmployeeStatus ");

		if (!UtilObject.getUtilObject().isNull(employeeStatus)) {

			if (UtilNumeric.getUtilNumeric().isGreatherThan(employeeStatus.getId(), 0)) {
				sb.append("WHERE").append(SPACE);
				sb.append("id = ? ");
				parameters.add(employeeStatus.getId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(employeeStatus.getName())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("name = ? ");
				parameters.add(UtilText.trim(employeeStatus.getName()));
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
					"There was a problem trying to find employeeStatus registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to find an employeeStatus registry on sql server", exception);

		}

		return results;

	}
	

	private List<EmployeeStatusDTO> executeQuery(PreparedStatement preparedStatement) {

		List<EmployeeStatusDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recover employeeStatus registry on sql server",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to execute the query for recover employeeStatus registry on sql server",
					exception);

		}

		return results;

	}

	private List<EmployeeStatusDTO> assembleResults(ResultSet resultSet) {
		List<EmployeeStatusDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {

				results.add(assembleDTO(resultSet));

			}

		} catch (QuotesException exception) {

			throw exception;

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException("There was a problem trying to recover the employeeStatus",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to recover the employeeStatus registry on sql server", exception);

		}

		return results;

	}

	private EmployeeStatusDTO assembleDTO(ResultSet resultSet) {

		EmployeeStatusDTO dto = new EmployeeStatusDTO();

		try {

			dto.setId(resultSet.getInt("id"));
			dto.setName(resultSet.getString("name"));

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException("There was a problem trying to assemble the employeeStatuss",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to assemble the employeeStatus on sql server", exception);

		}

		return dto;

	}


}
