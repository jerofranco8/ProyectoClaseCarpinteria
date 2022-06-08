package co.edu.uco.quotes.businesslogic.facade;

import java.util.List;

import co.edu.uco.quotes.dto.InventoryDTO;

public interface InventoryFacade {
	void create(InventoryDTO inventory);
	void update(InventoryDTO inventory);
	void delete(int id);
	List<InventoryDTO> find(InventoryDTO inventory);
}
