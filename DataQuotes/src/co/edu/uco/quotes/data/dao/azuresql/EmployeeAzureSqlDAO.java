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
import co.edu.uco.quotes.data.dao.EmployeeDAO;
import co.edu.uco.quotes.data.dao.connection.ConnectionSQL;
import co.edu.uco.quotes.dto.CampusDTO;
import co.edu.uco.quotes.dto.EmployeeDTO;
import co.edu.uco.quotes.dto.EmployeeStatusDTO;
import co.edu.uco.quotes.dto.EmployeeTypeDTO;
import co.edu.uco.quotes.dto.IdTypeDTO;

public class EmployeeAzureSqlDAO extends ConnectionSQL implements EmployeeDAO {

	protected EmployeeAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static EmployeeDAO build(Connection connection) {
		return new EmployeeAzureSqlDAO(connection);
	}

	@Override
	public void create(EmployeeDTO employee) {
		String sql = "INSERT INTO Employee (name, idType, idNumber, employeeType, employeeStatus, campus) VALUES(?,?,?,?,?,?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setInt(2, employee.getIdType().getId());
			preparedStatement.setString(3, employee.getIdNumber());
			preparedStatement.setInt(4, employee.getEmployeeType().getId());
			preparedStatement.setInt(5, employee.getEmployeeStatus().getId());
			preparedStatement.setInt(6, employee.getCampus().getId());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to create a new Employee registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to create a new Employee registry on sql server", exception);

		}
		
	}

	@Override
	public void update(EmployeeDTO employee) {
		String sql = "UPDATE Employee SET name = ?, idType=?, idNumber=?, employeeType=?, employeeStatus=?, campus=?  WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setInt(2, employee.getIdType().getId());
			preparedStatement.setString(3, employee.getIdNumber());
			preparedStatement.setInt(4, employee.getEmployeeType().getId());
			preparedStatement.setInt(5, employee.getEmployeeStatus().getId());
			preparedStatement.setInt(6, employee.getCampus().getId());
			preparedStatement.setInt(7, employee.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to update a new Employee registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to update Employee registry on sql server", exception);

		}	
		
	}

	@Override
	public void delete(int id) {

		String sql = "DELETE FROM Employee WHERE (id=?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1,id);
			
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to delete a Employee registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to delete a Employee registry on sql server", exception);

		
		}
	}

public List<EmployeeDTO> find(EmployeeDTO client) {


		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<EmployeeDTO> results = new ArrayList<EmployeeDTO>();

		StringBuilder sb = new StringBuilder(SPACE);
		sb.append("Select id, name, idType, idNumber, employeeType, employeeStatus, campus").append(SPACE);
		sb.append("From Employee ");

		if (!UtilObject.getUtilObject().isNull(client)) {

			if (UtilNumeric.getUtilNumeric().isGreatherThan(client.getId(), 0)) {
				sb.append("WHERE").append(SPACE);
				sb.append("id = ? ");
				parameters.add(client.getId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(client.getName())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("name = ? ");
				parameters.add(UtilText.trim(client.getName()));
				setWhere = false;
			}		
			
			if (UtilNumeric.getUtilNumeric().isGreatherThan(client.getIdType().getId(),0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("idType = ? ");
				parameters.add(client.getIdType().getId());
				setWhere = false;
			}
			
			if (!UtilText.isEmpty(client.getIdNumber())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("idNumber = ? ");
				parameters.add(UtilText.trim(client.getIdNumber()));
				setWhere = false;
			}
			
			if (UtilNumeric.getUtilNumeric().isGreatherThan(client.getEmployeeType().getId(),0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("employeeType = ? ");
				parameters.add(client.getEmployeeType().getId());
				setWhere = false;
			}
			
			if (UtilNumeric.getUtilNumeric().isGreatherThan(client.getEmployeeStatus().getId(),0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("employeeStatus = ? ");
				parameters.add(client.getEmployeeStatus().getId());
				setWhere = false;
			}
			
			if (UtilNumeric.getUtilNumeric().isGreatherThan(client.getCampus().getId(),0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("campus = ? ");
				parameters.add(client.getCampus().getId());
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
					"There was a problem trying to find empleoye registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to find an empleoye registry on sql server", exception);

		}

		return results;
		
	}
	
	private List<EmployeeDTO> executeQuery(PreparedStatement preparedStatement) {

		List<EmployeeDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recover empleoye registry on sql server",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to execute the query for recover empleoye registry on sql server",
					exception);

		}

		return results;

	}

	private List<EmployeeDTO> assembleResults(ResultSet resultSet) {
		List<EmployeeDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {

				results.add(assembleDTO(resultSet));

			}

		} catch (QuotesException exception) {

			throw exception;

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException("There was a problem trying to recover the empleoye",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to recover the empleoye registry on sql server", exception);

		}

		return results;

	}

	private EmployeeDTO assembleDTO(ResultSet resultSet) {

		EmployeeDTO dto = new EmployeeDTO();

		try {
			
			IdTypeDTO idType = new IdTypeDTO();
			idType.setId(resultSet.getInt("idType"));
			
			CampusDTO campus = new CampusDTO();
			campus.setId(resultSet.getInt("campus"));
			
			EmployeeTypeDTO employeeType = new EmployeeTypeDTO();
			employeeType.setId(resultSet.getInt("employeeType"));
			
			EmployeeStatusDTO employeeStastus = new EmployeeStatusDTO();
			employeeStastus.setId(resultSet.getInt("employeeStastus"));

			dto.setId(resultSet.getInt("id"));
			dto.setName(resultSet.getString("name"));
			dto.setIdType(idType);
			dto.setIdNumber(resultSet.getString("idNumber"));
			dto.setEmployeeType(employeeType);
			dto.setEmployeeStatus(employeeStastus);
			dto.setCampus(campus);

			
			

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException("There was a problem trying to assemble the empleoye",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to assemble the empleoye on sql server", exception);

		}

		return dto;

	}

}
