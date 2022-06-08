package co.edu.uco.quotes.businesslogic.business;

import java.util.List;

import co.edu.uco.quotes.dto.RawMaterialDTO;

public interface RawMaterialBusiness {
	void create(RawMaterialDTO rawMaterial);
	void update(RawMaterialDTO rawMaterial);
	void delete(int id);
	List<RawMaterialDTO> find(RawMaterialDTO material);
}
