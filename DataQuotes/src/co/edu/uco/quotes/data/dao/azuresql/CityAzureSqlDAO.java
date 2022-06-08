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
		String sql = "UPDATE City SET name = ? WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, city.getName());
			preparedStatement.setInt(2, city.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to update a new city registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to update city registry on sql server", exception);

		}

		
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
	public List<CityDTO> find(CityDTO City) {

		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<CityDTO> results = new ArrayList<CityDTO>();

		StringBuilder sb = new StringBuilder(SPACE);
		sb.append("Select id, name").append(SPACE);
		sb.append("From City ");

		if (!UtilObject.getUtilObject().isNull(City)) {

			if (UtilNumeric.getUtilNumeric().isGreatherThan(City.getId(), 0)) {
				sb.append("WHERE").append(SPACE);
				sb.append("id = ? ");
				parameters.add(City.getId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(City.getName())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("name = ? ");
				parameters.add(UtilText.trim(City.getName()));
				setWhere = false;
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
					"There was a problem trying to find city registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to find an city registry on sql server", exception);

		}

		return results;

	}
	

	private List<CityDTO> executeQuery(PreparedStatement preparedStatement) {

		List<CityDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recover cityregistry on sql server",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to execute the query for recover city registry on sql server",
					exception);

		}

		return results;

	}

	private List<CityDTO> assembleResults(ResultSet resultSet) {
		List<CityDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {

				results.add(assembleDTO(resultSet));

			}

		} catch (QuotesException exception) {

			throw exception;

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException("There was a problem trying to recover the citys",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to recover the citys registry on sql server", exception);

		}

		return results;

	}

	private CityDTO assembleDTO(ResultSet resultSet) {

		CityDTO dto = new CityDTO();

		try {

			dto.setId(resultSet.getInt("id"));
			dto.setName(resultSet.getString("name"));

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException("There was a problem trying to assemble the citys",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to assemble the citys on sql server", exception);

		}

		return dto;

	}


}
