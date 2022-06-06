package co.edu.uco.quotes.data.dao.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import co.edu.uco.quotes.crosscutting.exception.QuotesException;
import co.edu.uco.quotes.data.dao.ClientDAO;
import co.edu.uco.quotes.data.dao.connection.ConnectionSQL;
import co.edu.uco.quotes.dto.ClientDTO;


public class ClientAzureSqlDAO extends ConnectionSQL implements ClientDAO {

	protected ClientAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static ClientDAO build(Connection connection) {
		return new ClientAzureSqlDAO(connection);
	}

	@Override
	public void create(ClientDTO client) {
		String sql = "INSERT INTO Client (name, idType, idNumber, ) VALUES(?,?,?,?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, client.getName());
			preparedStatement.setInt(2, client.getIdType().getId());
			preparedStatement.setString(3, client.getIdNumber());
			preparedStatement.setObject(4, client.getQuotations());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to create a new campus registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to create a new campus registry on sql server", exception);

		}		
	}

	@Override
	public void update(ClientDTO client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ClientDTO> find(ClientDTO client) {
		return null;
		
	}

	

}
