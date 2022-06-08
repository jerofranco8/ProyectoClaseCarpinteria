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
import co.edu.uco.quotes.data.dao.InventoryDAO;
import co.edu.uco.quotes.data.dao.connection.ConnectionSQL;
import co.edu.uco.quotes.dto.InventoryDTO;
import co.edu.uco.quotes.dto.CampusDTO;

public class InventoryAzureSqlDAO extends ConnectionSQL implements InventoryDAO {

	protected InventoryAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static InventoryDAO build(Connection connection) {
		return new InventoryAzureSqlDAO(connection);
	}

	@Override
	public void create(InventoryDTO inventory) {
		String sql = "INSERT INTO Inventory (name, campus) VALUES(?,?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, inventory.getName());
			preparedStatement.setInt(2, inventory.getCampus().getId());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to create a new inventory registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to create a new inventory registry on sql server", exception);

		}
	}

	@Override
	public void update(InventoryDTO inventory) {
		String sql = "UPDATE Inventory SET name = ?, campus = ?  WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, inventory.getName());
			preparedStatement.setInt(2, inventory.getCampus().getId());
			preparedStatement.setInt(3, inventory.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to update a new inventory registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to update inventory registry on sql server", exception);

		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM Inventory WHERE (id=?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1,id);
			
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to delete a Inventory registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to delete a Inventory registry on sql server", exception);

		}
		
	}

	@Override
	public List<InventoryDTO> find(InventoryDTO inventory) {
		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<InventoryDTO> results = new ArrayList<InventoryDTO>();

		StringBuilder sb = new StringBuilder(SPACE);
		sb.append("Select id, name, campus").append(SPACE);
		sb.append("From Inventory ");

		if (!UtilObject.getUtilObject().isNull(inventory)) {

			if (UtilNumeric.getUtilNumeric().isGreatherThan(inventory.getId(), 0)) {
				sb.append("WHERE").append(SPACE);
				sb.append("id = ? ");
				parameters.add(inventory.getId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(inventory.getName())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("name = ? ");
				parameters.add(UtilText.trim(inventory.getName()));
				setWhere = false;
			}
			
			if (UtilNumeric.getUtilNumeric().isGreatherThan(inventory.getCampus().getId(),0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("campus = ? ");
				parameters.add(inventory.getCampus().getId());
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
					"There was a problem trying to find inventorye registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to find an inventory registry on sql server", exception);

		}

		return results;
		
	}
	
	private List<InventoryDTO> executeQuery(PreparedStatement preparedStatement) {

		List<InventoryDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recover inventory registry on sql server",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to execute the query for recover inventory registry on sql server",
					exception);

		}

		return results;

	}

	private List<InventoryDTO> assembleResults(ResultSet resultSet) {
		List<InventoryDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {

				results.add(assembleDTO(resultSet));

			}

		} catch (QuotesException exception) {

			throw exception;

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException("There was a problem trying to recover the inventory",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to recover the inventorys registry on sql server", exception);

		}

		return results;

	}

	private InventoryDTO assembleDTO(ResultSet resultSet) {

		InventoryDTO dto = new InventoryDTO();

		try {
			CampusDTO campus = new CampusDTO();
			campus.setId(resultSet.getInt("campus"));
			
			
			dto.setId(resultSet.getInt("id"));
			dto.setName(resultSet.getString("name"));
			dto.setCampus(campus);
		
			
			

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException("There was a problem trying to assemble the inventoryss",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to assemble the id inventorys on sql server", exception);

		}

		return dto;

	}

}
