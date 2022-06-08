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
import co.edu.uco.quotes.data.dao.StockDAO;
import co.edu.uco.quotes.data.dao.connection.ConnectionSQL;
import co.edu.uco.quotes.dto.InventoryDTO;
import co.edu.uco.quotes.dto.RawMaterialDTO;
import co.edu.uco.quotes.dto.StockDTO;

public class StockAzureSqlDAO extends ConnectionSQL implements StockDAO {

	protected StockAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static StockDAO build(Connection connection) {
		return new StockAzureSqlDAO(connection);
	}

	@Override
	public void create(StockDTO stock) {
//		private int id;
//		private String name;
//		private int unit;
//		private RawMaterialDTO rawMaterial;
//		private InventoryDTO inventory;

		
		String sql = "INSERT INTO Stock (name, rawMaterial, unit, inventory) VALUES(?,?,?,?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, stock.getName());
			preparedStatement.setInt(2, stock.getRawMaterial().getId());
			preparedStatement.setInt(3, stock.getUnit());
			preparedStatement.setInt(4, stock.getInventory().getId());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to create a new Stock registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to create a new Stock registry on sql server", exception);

		}
		
	}

	@Override
	public void update(StockDTO stock) {
		String sql = "UPDATE Stock SET name = ?, rawMaterial=?, unit=?, inventory=?  WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, stock.getName());
			preparedStatement.setInt(2, stock.getRawMaterial().getId());
			preparedStatement.setInt(3, stock.getUnit());
			preparedStatement.setInt(4, stock.getInventory().getId());
			preparedStatement.setInt(5, stock.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to update a new Stock registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to update Stock registry on sql server", exception);

		}
	}

	@Override
	public void delete(int id) {

		String sql = "DELETE FROM Stock WHERE (id=?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1,id);
			
		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to delete a Stock registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to delete a Stock registry on sql server", exception);

		
		}
		
	}
	
	@Override
	public List<StockDTO> find(StockDTO stock) {
	
		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<StockDTO> results = new ArrayList<StockDTO>();

		StringBuilder sb = new StringBuilder(SPACE);
		sb.append("Select id, name, rawMaterial, unit, inventory").append(SPACE);
		sb.append("From Stock ");

		if (!UtilObject.getUtilObject().isNull(stock)) {

			if (UtilNumeric.getUtilNumeric().isGreatherThan(stock.getId(), 0)) {
				sb.append("WHERE").append(SPACE);
				sb.append("id = ? ");
				parameters.add(stock.getId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(stock.getName())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("name = ? ");
				parameters.add(UtilText.trim(stock.getName()));
				setWhere = false;
			}
			
			if (UtilNumeric.getUtilNumeric().isGreatherThan(stock.getRawMaterial().getId(),0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("rawMaterial = ? ");
				parameters.add(stock.getRawMaterial().getId());
				setWhere = false;
			}			
			
		
			if (UtilNumeric.getUtilNumeric().isGreatherThan(stock.getUnit(),0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("unit = ? ");
				parameters.add(stock.getUnit());
				setWhere = false;
			}
			
			if (UtilNumeric.getUtilNumeric().isGreatherThan(stock.getInventory().getId(),0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("inventory = ? ");
				parameters.add(stock.getInventory().getId());
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
					"There was a problem trying to find stocke registry on sql server", exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to find an stock registry on sql server", exception);

		}

		return results;
		
	}
	
	private List<StockDTO> executeQuery(PreparedStatement preparedStatement) {

		List<StockDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recover stock registry on sql server",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to execute the query for recover stock registry on sql server",
					exception);

		}

		return results;

	}

	private List<StockDTO> assembleResults(ResultSet resultSet) {
		List<StockDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {

				results.add(assembleDTO(resultSet));

			}

		} catch (QuotesException exception) {

			throw exception;

		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException("There was a problem trying to recover the stock",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to recover the stocks registry on sql server", exception);

		}

		return results;

	}

	private StockDTO assembleDTO(ResultSet resultSet) {

		StockDTO dto = new StockDTO();

		try {
			
			InventoryDTO inventory = new InventoryDTO();
			inventory.setId(resultSet.getInt("inventory"));
			
			RawMaterialDTO rawMaterial = new RawMaterialDTO();
			rawMaterial.setId(resultSet.getInt("rawMaterial"));
			
			dto.setId(resultSet.getInt("id"));
			dto.setName(resultSet.getString("name"));
			dto.setRawMaterial(rawMaterial);
			dto.setUnit(resultSet.getInt("unit"));
			dto.setInventory(inventory);
			


		} catch (SQLException exception) {

			throw QuotesException.buildTechnicalDataException("There was a problem trying to assemble the stocks",
					exception);

		} catch (Exception exception) {

			throw QuotesException.buildTechnicalDataException(
					"There was an unexpected problem trying to assemble the id stocks on sql server", exception);

		}

		return dto;

	}


	
}