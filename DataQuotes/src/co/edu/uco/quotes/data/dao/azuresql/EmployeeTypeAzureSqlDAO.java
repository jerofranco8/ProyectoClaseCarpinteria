package co.edu.uco.quotes.data.dao.azuresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.quotes.data.dao.EmployeeTypeDAO;
import co.edu.uco.quotes.data.dao.connection.ConnectionSQL;
import co.edu.uco.quotes.dto.EmployeeTypeDTO;

public class EmployeeTypeAzureSqlDAO extends ConnectionSQL implements EmployeeTypeDAO {

	protected EmployeeTypeAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static EmployeeTypeDAO build(Connection connection) {
		return new EmployeeTypeAzureSqlDAO(connection);
	}{

}

	@Override
	public void create(EmployeeTypeDTO employeeType) {

		
	}

	@Override
	public void update(EmployeeTypeDTO employeeType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EmployeeTypeDTO> find(EmployeeTypeDTO employeeType) {
		return null;
		// TODO Auto-generated method stub
		
	}
}
