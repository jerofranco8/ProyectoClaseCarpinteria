package co.edu.uco.quotes.data.dao.azuresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.quotes.data.dao.StockDAO;
import co.edu.uco.quotes.data.dao.connection.ConnectionSQL;

import co.edu.uco.quotes.dto.StockDTO;

public class StockAzureSqlDAO extends ConnectionSQL implements StockDAO {

	protected StockAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static StockDAO build(Connection connection) {
		return new StockAzureSqlDAO(connection);
	}

	@Override
	public void create(StockDTO stock) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(StockDTO stock) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<StockDTO> find(StockDTO stock) {
		// TODO Auto-generated method stub
		return null;
	}

	
}