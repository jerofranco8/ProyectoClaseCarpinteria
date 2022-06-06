package co.edu.uco.quotes.data.dao.azuresql;

import java.sql.Connection;
import java.util.List;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(EmployeeStatusDTO employeeStatus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EmployeeStatusDTO> find(EmployeeStatusDTO employeeStatus) {
		return null;
		// TODO Auto-generated method stub
		
	}

}
