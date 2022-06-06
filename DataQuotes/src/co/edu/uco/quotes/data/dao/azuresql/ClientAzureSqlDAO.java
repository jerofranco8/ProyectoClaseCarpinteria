package co.edu.uco.quotes.data.dao.azuresql;

import java.sql.Connection;
import java.util.List;

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
		// TODO Auto-generated method stub
		
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
