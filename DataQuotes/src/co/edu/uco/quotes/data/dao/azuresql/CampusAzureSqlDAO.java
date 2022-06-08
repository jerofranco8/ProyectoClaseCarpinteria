package co.edu.uco.quotes.data.dao.azuresql;

import static co.edu.uco.crosscutting.util.text.UtilText.SPACE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;
import co.edu.uco.quotes.crosscutting.exception.QuotesException;
import co.edu.uco.quotes.data.dao.CampusDAO;
import co.edu.uco.quotes.data.dao.connection.ConnectionSQL;
import co.edu.uco.quotes.dto.CampusDTO;
import co.edu.uco.quotes.dto.CityDTO;
import co.edu.uco.quotes.dto.InventoryDTO;

public class CampusAzureSqlDAO extends ConnectionSQL implements CampusDAO {

	protected CampusAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static CampusDAO build(Connection connection) {
		return new CampusAzureSqlDAO(connection);
	}

	@Override
	public void create(CampusDTO campus) {
		String sql = "INSERT INTO Campus (name, city, direction) VALUES(?,?,?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, campus.getName());
			preparedStatement.setInt(2, campus.getCity().getId());
			preparedStatement.setString(3, campus.getDirection());
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
		String sql = "UPDATE Campus SET name = ?, city=?, direction=?  WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, campus.getName());
			preparedStatement.setInt(2, campus.getCity().getId());
			preparedStatement.setString(3, campus.getDirection());
			preparedStatement.setInt(4, campus.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to update a new campus registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to update campus registry on sql server", exception);

		}
		
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
		
//		private int id;
//		private String name;
//		private CityDTO city;
//		private InventoryDTO inventory;
//		private String direction;
		

		
		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<CampusDTO> results = new ArrayList<CampusDTO>();

		StringBuilder sb = new StringBuilder(SPACE);
		sb.append("Select id, name, city, direction").append(SPACE);
		sb.append("From Campus ");

		if (!UtilObject.getUtilObject().isNull(campus)) {

			if (UtilNumeric.getUtilNumeric().isGreatherThan(campus.getId(), 0)) {
				sb.append("WHERE").append(SPACE);
				sb.append("id = ? ");
				parameters.add(campus.getId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(campus.getName())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("name = ? ");
				parameters.add(UtilText.trim(campus.getName()));
				setWhere = false;
			}
			
			if (UtilNumeric.getUtilNumeric().isGreatherThan(campus.getCity().getId(),0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("city = ? ");
				parameters.add(campus.getCity().getId());
				setWhere = false;
			}			
			
		
			if (!UtilText.isEmpty(campus.getDirection())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("direction = ? ");
				parameters.add(UtilText.trim(campus.getDirection()));
				
			}

		}

		sb.append("ORDER BY name ASC");

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sb.toString())) {

			for (int index = 0; index < parameters.size(); index++) {
				preparedStatement.setObject(index + 1, parameters.get(index));
			}

			results = executeQuery(preparedStatement);

		} catch (QuotesException exception) {
			throw exception;

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to find campuse registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to find an campus registry on sql server", exception);

		}

		return results;
		
	}
	
	private List<CampusDTO> executeQuery(PreparedStatement preparedStatement) {

		List<CampusDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recover campus registry on sql server",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to execute the query for recover campus registry on sql server",
					exception);

		}

		return results;

	}

	private List<CampusDTO> assembleResults(ResultSet resultSet) {
		List<CampusDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {

				results.add(assembleDTO(resultSet));

			}

		} catch (QuotesException exception) {

			throw exception;

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException("There was a problem trying to recover the campus",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to recover the campuss registry on sql server", exception);

		}

		return results;

	}

	private CampusDTO assembleDTO(ResultSet resultSet) {

		CampusDTO dto = new CampusDTO();

		try {
			CityDTO city = new CityDTO();
			city.setId(resultSet.getInt("city"));
			
			
			dto.setId(resultSet.getInt("id"));
			dto.setName(resultSet.getString("name"));
			dto.setCity(city);
			dto.setDirection(resultSet.getString("direction"));
			
			

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException("There was a problem trying to assemble the campusss",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to assemble the id campuss on sql server", exception);

		}

		return dto;

	}


	
	
}
