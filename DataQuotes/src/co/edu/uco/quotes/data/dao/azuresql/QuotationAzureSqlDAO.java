package co.edu.uco.quotes.data.dao.azuresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.quotes.data.dao.QuotationDAO;
import co.edu.uco.quotes.data.dao.connection.ConnectionSQL;
import co.edu.uco.quotes.dto.QuotationDTO;

public class QuotationAzureSqlDAO extends ConnectionSQL implements QuotationDAO {

	protected QuotationAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static QuotationDAO build(Connection connection) {
		return new QuotationAzureSqlDAO(connection);
	}

	@Override
	public void create(QuotationDTO quotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(QuotationDTO quotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<QuotationDTO> find(QuotationDTO quotation) {
		return null;
		// TODO Auto-generated method stub
		
	}

}
