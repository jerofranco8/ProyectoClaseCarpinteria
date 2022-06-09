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
import co.edu.uco.quotes.data.dao.EmployeeTypeDAO;
import co.edu.uco.quotes.data.dao.connection.ConnectionSQL;
import co.edu.uco.quotes.dto.EmployeeTypeDTO;

public class EmployeeTypeAzureSqlDAO extends ConnectionSQL implements EmployeeTypeDAO {

	protected EmployeeTypeAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static EmployeeTypeDAO build(Connection connection) {
		return new EmployeeTypeAzureSqlDAO(connection);
	}


	@Override
	public void create(EmployeeTypeDTO employeeType) {

		String sql = "INSERT INTO EmployeeType (name, salary, [function]) VALUES(?,?,?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1,employeeType.getName());
			preparedStatement.setInt(2,employeeType.getSalary());
			preparedStatement.setString(3,employeeType.getFunction());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to create a new employee type registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to create a new employee type registry on sql server", exception);

		}
	}

	@Override
	public void update(EmployeeTypeDTO employeeType) {
		String sql = "UPDATE EmployeeType SET name = ?, salary=?, [function]=?  WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, employeeType.getName());
			preparedStatement.setInt(2, employeeType.getSalary());
			preparedStatement.setString(3, employeeType.getFunction());
			preparedStatement.setInt(4, employeeType.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to update a employee type registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to update employee type registry on sql server", exception);

		}
		
	}
		
	
	@Override
	public void delete(int id) {	
		String sql = "DELETE FROM EmployeeType WHERE id=?";
		
		try(PreparedStatement preparedStatement= getConnection().prepareStatement(sql)){
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to delete an employee type registry on sql server", exception);
		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to delete an employee type registry on sql server", exception);

		}
	}

	@Override
	public List<EmployeeTypeDTO> find(EmployeeTypeDTO employeeType) {

		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<EmployeeTypeDTO> results = new ArrayList<EmployeeTypeDTO>();

		StringBuilder sb = new StringBuilder(SPACE);
		sb.append("Select id, name, salary, [function]").append(SPACE);
		sb.append("From EmployeeType ");

		if (!UtilObject.getUtilObject().isNull(employeeType)) {

			if (UtilNumeric.getUtilNumeric().isGreatherThan(employeeType.getId(), 0)) {
				sb.append("WHERE").append(SPACE);
				sb.append("id = ? ");
				parameters.add(employeeType.getId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(employeeType.getName())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("name = ? ");
				parameters.add(UtilText.trim(employeeType.getName()));
				setWhere = false;
			}
			
			if (UtilNumeric.getUtilNumeric().isGreatherThan(employeeType.getSalary(),0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("salary = ? ");
				parameters.add(employeeType.getSalary());
				setWhere = false;
			}			
			
		
			if (!UtilText.isEmpty(employeeType.getFunction())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("[function] = ? ");
				parameters.add(UtilText.trim(employeeType.getFunction()));
				setWhere = false;
			}

		}

		sb.append("ORDER BY name ASC");
		
		System.out.println(sb.toString());

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sb.toString())) {

			for (int index = 0; index < parameters.size(); index++) {
				preparedStatement.setObject(index + 1, parameters.get(index));
			}

			results = executeQuery(preparedStatement);

		} catch (QuotesException exception) {
			throw exception;

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to find employeeTypee registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to find an employeeType registry on sql server", exception);

		}

		return results;
		
	}
	
	private List<EmployeeTypeDTO> executeQuery(PreparedStatement preparedStatement) {

		List<EmployeeTypeDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recover employeeType registry on sql server",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to execute the query for recover employeeType registry on sql server",
					exception);

		}

		return results;

	}

	private List<EmployeeTypeDTO> assembleResults(ResultSet resultSet) {
		List<EmployeeTypeDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {

				results.add(assembleDTO(resultSet));

			}

		} catch (QuotesException exception) {

			throw exception;

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException("There was a problem trying to recover the employeeType",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to recover the employeeTypes registry on sql server", exception);

		}

		return results;

	}

	private EmployeeTypeDTO assembleDTO(ResultSet resultSet) {

		EmployeeTypeDTO dto = new EmployeeTypeDTO();

		try {
			
			dto.setId(resultSet.getInt("id"));
			dto.setName(resultSet.getString("name"));
			dto.setSalary(resultSet.getInt("salary"));
			dto.setFunction(resultSet.getString("function"));
			
			

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException("There was a problem trying to assemble the employeeTypess",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to assemble the id employeeTypes on sql server", exception);

		}

		return dto;

	}


}
