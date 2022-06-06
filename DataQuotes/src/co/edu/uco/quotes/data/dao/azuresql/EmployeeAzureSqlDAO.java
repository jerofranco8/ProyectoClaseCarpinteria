package co.edu.uco.quotes.data.dao.azuresql;

import java.sql.Connection;
import java.util.List;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(EmployeeDTO employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EmployeeDTO> find(EmployeeDTO employee) {
		return null;		
	}

}
