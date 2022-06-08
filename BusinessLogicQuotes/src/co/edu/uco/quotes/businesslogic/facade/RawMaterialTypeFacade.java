package co.edu.uco.quotes.businesslogic.facade;

import java.util.List;

import co.edu.uco.quotes.dto.RawMaterialTypeDTO;

public interface RawMaterialTypeFacade {
	void create(RawMaterialTypeDTO rawMaterialType);
	void update(RawMaterialTypeDTO rawMaterialType);
	void delete(int id);
	List<RawMaterialTypeDTO> find(RawMaterialTypeDTO materialType);
}
