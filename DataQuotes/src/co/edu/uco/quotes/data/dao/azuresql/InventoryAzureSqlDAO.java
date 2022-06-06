package co.edu.uco.quotes.data.dao.azuresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.quotes.data.dao.InventoryDAO;
import co.edu.uco.quotes.data.dao.connection.ConnectionSQL;
import co.edu.uco.quotes.dto.InventoryDTO;

public class InventoryAzureSqlDAO extends ConnectionSQL implements InventoryDAO {

	protected InventoryAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static InventoryDAO build(Connection connection) {
		return new InventoryAzureSqlDAO(connection);
	}

	@Override
	public void create(InventoryDTO inventory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(InventoryDTO inventory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<InventoryDTO> find(InventoryDTO inventory) {
		return null;
		// TODO Auto-generated method stub
		
	}

}
