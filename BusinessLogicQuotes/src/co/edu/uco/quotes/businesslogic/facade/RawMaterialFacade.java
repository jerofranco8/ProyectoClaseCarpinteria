package co.edu.uco.quotes.businesslogic.facade;

import java.util.List;

import co.edu.uco.quotes.dto.RawMaterialDTO;

public interface RawMaterialFacade {
	void create(RawMaterialDTO rawMaterial);
	void update(RawMaterialDTO rawMaterial);
	void delete(int id);
	List<RawMaterialDTO> find(RawMaterialDTO material);
}
