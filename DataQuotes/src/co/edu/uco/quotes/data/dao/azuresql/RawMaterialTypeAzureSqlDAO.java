package co.edu.uco.quotes.data.dao.azuresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.quotes.data.dao.RawMaterialTypeDAO;
import co.edu.uco.quotes.data.dao.connection.ConnectionSQL;
import co.edu.uco.quotes.dto.RawMaterialTypeDTO;

public class RawMaterialTypeAzureSqlDAO extends ConnectionSQL implements RawMaterialTypeDAO {

	protected RawMaterialTypeAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static RawMaterialTypeDAO build(Connection connection) {
		return new RawMaterialTypeAzureSqlDAO(connection);
	}

	@Override
	public void create(RawMaterialTypeDTO rawMaterialType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(RawMaterialTypeDTO rawMaterialType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RawMaterialTypeDTO> find(RawMaterialTypeDTO rawMaterialType) {
		return null;
		// TODO Auto-generated method stub
		
	}

}
