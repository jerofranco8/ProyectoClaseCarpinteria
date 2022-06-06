package co.edu.uco.quotes.data.dao.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import co.edu.uco.quotes.crosscutting.exception.QuotesException;
import co.edu.uco.quotes.data.dao.CampusDAO;
import co.edu.uco.quotes.data.dao.connection.ConnectionSQL;
import co.edu.uco.quotes.dto.CampusDTO;

public class CampusAzureSqlDAO extends ConnectionSQL implements CampusDAO {

	protected CampusAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static CampusDAO build(Connection connection) {
		return new CampusAzureSqlDAO(connection);
	}

	@Override
	public void create(CampusDTO campus) {
		String sql = "INSERT INTO Campus (name, city, Inventory, Direction) VALUES(?,?,?,?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, campus.getName());
			preparedStatement.setInt(2, campus.getCity().getId());
			preparedStatement.setInt(3, campus.getInventory().getId());
			preparedStatement.setString(4, campus.getDirection());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to create a new campus registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to create a new campus registry on sql server", exception);

		}
		
	}

	@Override
	public void update(CampusDTO campus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM Campus WHERE (id=?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1,id);
			
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to delete a campus registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to delete a campus registry on sql server", exception);

		}
	}

	@Override
	public List<CampusDTO> Find(CampusDTO campus) {
		// TODO Auto-generated method stub
		return null;
	} 
}
