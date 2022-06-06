package co.edu.uco.quotes.data.factory;

import java.sql.Connection;

import co.edu.uco.quotes.data.factory.azuresql.AzureSqlDAOFactory;
import co.edu.uco.quotes.data.dao.CampusDAO;
import co.edu.uco.quotes.data.dao.CityDAO;
import co.edu.uco.quotes.data.dao.ClientDAO;
import co.edu.uco.quotes.data.dao.EmployeeDAO;
import co.edu.uco.quotes.data.dao.EmployeeStatusDAO;
import co.edu.uco.quotes.data.dao.EmployeeTypeDAO;
import co.edu.uco.quotes.data.dao.IdTypeDAO;
import co.edu.uco.quotes.data.dao.InventoryDAO;
import co.edu.uco.quotes.data.dao.QuotationDAO;
import co.edu.uco.quotes.data.dao.QuotationTypeDAO;
import co.edu.uco.quotes.data.dao.RawMaterialDAO;
import co.edu.uco.quotes.data.dao.RawMaterialTypeDAO;
import co.edu.uco.quotes.data.dao.StockDAO;

public abstract class DAOFactory {
	
	public static DAOFactory getDaoFactory() {
		return AzureSqlDAOFactory.create();
	}
	
	protected abstract void openConnection();
	
	protected abstract Connection getConnection();
	
	public abstract void initTransaction();
	
	public abstract void closeConnection();
	
	public abstract void rollbackTransaction();
	
	public abstract void commitTransaction();
	
	
	public abstract CampusDAO getCampusDAO();
	
	public abstract CityDAO getCityDAO();
	
	public abstract ClientDAO getClientDAO();
	
	public abstract EmployeeDAO getEmployeeDAO();
	
	public abstract EmployeeStatusDAO getEmployeeStatusDAO();

	public abstract EmployeeTypeDAO getEmployeeTypeDAO();
	
	public abstract IdTypeDAO getIdTypeDAO();
	
	public abstract InventoryDAO getInventoryDAO();
	
	public abstract QuotationDAO getQuotationDAO();
	
	public abstract QuotationTypeDAO getQuotationTypeDAO();
	
	public abstract RawMaterialDAO getMaterialDAO();
	
	public abstract RawMaterialTypeDAO getMaterialTypeDAO();
	
	public abstract StockDAO getStockDAO();
	
	
}
	
	
	
	
	

