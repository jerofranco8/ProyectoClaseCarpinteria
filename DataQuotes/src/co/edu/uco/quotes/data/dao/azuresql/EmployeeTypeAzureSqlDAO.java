package co.edu.uco.quotes.data.dao.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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

		String sql = "INSERT INTO EmployeeType (name, salary, function) VALUES(?,?,?)";

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
		// TODO Auto-generated method stub
		
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
		return null;
		// TODO Auto-generated method stub
		
	}
}
