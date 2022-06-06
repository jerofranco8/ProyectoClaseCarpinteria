package co.edu.uco.quotes.data.dao.azuresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.quotes.data.dao.RawMaterialDAO;
import co.edu.uco.quotes.data.dao.connection.ConnectionSQL;
import co.edu.uco.quotes.dto.RawMaterialDTO;

public class RawMaterialAzureSqlDAO extends ConnectionSQL implements RawMaterialDAO {

	protected RawMaterialAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static RawMaterialDAO build(Connection connection) {
		return new RawMaterialAzureSqlDAO(connection);
	}

	@Override
	public void create(RawMaterialDTO rawMaterial) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(RawMaterialDTO rawMaterial) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RawMaterialDTO> find(RawMaterialDTO rawMaterial) {
		return null;
		// TODO Auto-generated method stub
		
	}

}
