package co.edu.uco.quotes.data.dao.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import co.edu.uco.quotes.crosscutting.exception.QuotesException;
import co.edu.uco.quotes.data.dao.EmployeeDAO;
import co.edu.uco.quotes.data.dao.connection.ConnectionSQL;
import co.edu.uco.quotes.dto.EmployeeDTO;

public class EmployeeAzureSqlDAO extends ConnectionSQL implements EmployeeDAO {

	protected EmployeeAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static EmployeeDAO build(Connection connection) {
		return new EmployeeAzureSqlDAO(connection);
	}

	@Override
	public void create(EmployeeDTO employee) {
		String sql = "INSERT INTO Employee (name, idType, idNumber, empleType, employeeStatus, campus) VALUES(?,?,?,?,?,?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setInt(2, employee.getIdType().getId());
			preparedStatement.setString(3, employee.getIdNumber());
			preparedStatement.setInt(4, employee.getEmpleType().getId());
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
		// TODO Auto-generated method stub
		
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

	@Override
	public List<EmployeeDTO> find(EmployeeDTO employee) {
		return null;		
	}

}
