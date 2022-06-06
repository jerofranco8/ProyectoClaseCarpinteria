package co.edu.uco.quotes.data.dao;

import java.util.List;

import co.edu.uco.quotes.dto.InventoryDTO;

public interface InventoryDAO {
	void create(InventoryDTO inventory);
	void update(InventoryDTO inventory);
	void delete(int id);
	List<InventoryDTO> find(InventoryDTO inventory);
}
