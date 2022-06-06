package co.edu.uco.quotes.data.factory.azuresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.crosscutting.util.sql.UtilConnection;
import co.edu.uco.quotes.crosscutting.exception.QuotesException;
import co.edu.uco.quotes.crosscutting.exception.enumeration.ExceptionLocation;
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
import co.edu.uco.quotes.data.factory.DAOFactory;

public class AzureSqlDAOFactory extends DAOFactory {

	private Connection connection;

	private AzureSqlDAOFactory() {
		openConnection();
	}
	
	public static DAOFactory create() {
		return new AzureSqlDAOFactory();
	}

	@Override
	protected void openConnection() {

		String stringConnection = "jdbc:sqlserver://academic-database-server.database.windows.net:1433;database=academic-db;user=academicDmlUser;password=4c4d3m1cDmlUs3r;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		try {
			connection = DriverManager.getConnection(stringConnection);

		} catch (SQLException exception) {
			throw QuotesException.buildTechnicalException(
					"There was a problem trying to get the connection with SQL server at jdbc:sqlserver://academic-database-server.database.windows.net:1433;database=academic-db;user=academicDmlUser",
					exception, ExceptionLocation.DATA);
		} catch (Exception exception) {
			throw QuotesException.buildTechnicalException(
					"An unexpected problem has ocurred while trying to get the connection with SQL server", exception,
					ExceptionLocation.DATA);

		}

	}

	@Override
	protected Connection getConnection() {
		return connection;

	}

	@Override
	public void closeConnection() {
		if (UtilConnection.isClosed(getConnection())) {
			throw QuotesException
					.buildTechnicalException("It's not possible to close a connection because the conection is closed");
		}

		try {
			getConnection().close();

		} catch (SQLException exception) {
			throw QuotesException.buildTechnicalException(
					"There was a problem trying to close the connection with the SQL Server", exception,
					ExceptionLocation.DATA);
		} catch (Exception exception) {
			throw QuotesException.buildTechnicalException(
					"An unexpected problem has ocurred while trying to close the connection with the SQL Server",
					exception, ExceptionLocation.DATA);

		}

	}

	@Override
	public void rollbackTransaction() {
		if (UtilConnection.isClosed(connection)) {
			throw QuotesException.buildTechnicalException(
					"It's not possible to rollback a connection because the conection is closed");
		}

		try {
			if (getConnection().getAutoCommit()) {
				throw QuotesException.buildTechnicalException(
						"It's not possible to rollback a connection because the SQL server is managing the transaction");
			}

			getConnection().rollback();

		} catch (QuotesException exception) {
			throw exception;

		} catch (SQLException exception) {
			throw QuotesException.buildTechnicalException("There was a problem trying to rollback the transaction",
					exception, ExceptionLocation.DATA);
		} catch (Exception exception) {
			throw QuotesException.buildTechnicalException(
					"An unexpected problem has ocurred while trying to rollback the transaction", exception,
					ExceptionLocation.DATA);

		}

	}

	@Override
	public void initTransaction() {
		if (UtilConnection.isClosed(connection)) {
			throw QuotesException.buildTechnicalException(
					"It's not possible to initialize a connection because the conection is already closed");
		}

		try {
			if (!getConnection().getAutoCommit()) {
				throw QuotesException.buildTechnicalException(
						"It's not possible to initialize a connection because it is already initialized");
			}
			getConnection().setAutoCommit(false);

		} catch (SQLException exception) {
			throw QuotesException.buildTechnicalException("There was a problem trying to initialize the transaction",
					exception, ExceptionLocation.DATA);
		} catch (Exception exception) {
			throw QuotesException.buildTechnicalException(
					"An unexpected problem has ocurred while trying to initialize the transaction", exception,
					ExceptionLocation.DATA);

		}

	}

	@Override
	public void commitTransaction() {
		if (UtilConnection.isClosed(connection)) {
			throw QuotesException.buildTechnicalException(
					"It's not possible to commit a connection because the conection is already closed");
		}

		try {

			if (getConnection().getAutoCommit()) {
				throw QuotesException.buildTechnicalException(
						"It's not possible to commit a connection because the SQL server is managing the transaction");
			}

			getConnection().commit();

		} catch (QuotesException exception) {
			throw exception;

		} catch (SQLException exception) {
			throw QuotesException.buildTechnicalException("There was a problem trying to commit the transaction",
					exception, ExceptionLocation.DATA);
		} catch (Exception exception) {
			throw QuotesException.buildTechnicalException(
					"An unexpected problem has ocurred while trying to commit the transaction", exception,
					ExceptionLocation.DATA);

		}

	}

	@Override
	public CampusDAO getCampusDAO() {
		return null;
	}

	@Override
	public CityDAO getCityDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientDAO getClientDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeDAO getEmployeeDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeStatusDAO getEmployeeStatusDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeTypeDAO getEmployeeTypeDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IdTypeDAO getIdTypeDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InventoryDAO getInventoryDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuotationDAO getQuotationDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuotationTypeDAO getQuotationTypeDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RawMaterialDAO getMaterialDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RawMaterialTypeDAO getMaterialTypeDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockDAO getStockDAO() {
		return null;
	}


}
