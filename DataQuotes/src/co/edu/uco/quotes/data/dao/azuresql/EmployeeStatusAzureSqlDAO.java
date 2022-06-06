package co.edu.uco.quotes.data.dao.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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
		String sql = "INSERT INTO EmployeeStatus(name) VALUES(?)";

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
		// TODO Auto-generated method stub
		
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
		return null;
		// TODO Auto-generated method stub
		
	}

}
