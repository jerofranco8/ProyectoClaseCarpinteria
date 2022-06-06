package co.edu.uco.quotes.data.dao.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import co.edu.uco.quotes.crosscutting.exception.QuotesException;
import co.edu.uco.quotes.data.dao.CityDAO;
import co.edu.uco.quotes.data.dao.connection.ConnectionSQL;
import co.edu.uco.quotes.dto.CityDTO;


public class CityAzureSqlDAO extends ConnectionSQL implements CityDAO {

	protected CityAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static CityDAO build(Connection connection) {
		return new CityAzureSqlDAO(connection);
	}

	@Override
	public void create(CityDTO city) {
		String sql = "INSERT INTO City (name) VALUES(?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, city.getName());
		
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to create a new city registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to create a new city registry on sql server", exception);

		}
		
	}

	@Override
	public void update(CityDTO city) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM City WHERE (id=?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1,id);
			
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to delete a city registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to delete a city registry on sql server", exception);

		}
		
	}

	@Override
	public List<CityDTO> find(CityDTO city) {
		// TODO Auto-generated method stub
		return null;
	}



}
