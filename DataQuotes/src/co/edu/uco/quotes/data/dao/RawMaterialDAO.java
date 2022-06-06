package co.edu.uco.quotes.data.dao;

import java.util.List;

import co.edu.uco.quotes.dto.RawMaterialDTO;

public interface RawMaterialDAO {
	void create(RawMaterialDTO rawMaterial);
	void update(RawMaterialDTO rawMaterial);
	void delete(int id);
	List<RawMaterialDTO> find(RawMaterialDTO material);
}
