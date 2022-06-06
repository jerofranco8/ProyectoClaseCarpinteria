package co.edu.uco.quotes.data.dao;

import java.util.List;

import co.edu.uco.quotes.dto.RawMaterialTypeDTO;

public interface RawMaterialTypeDAO {
	void create(RawMaterialTypeDTO rawMaterialType);
	void update(RawMaterialTypeDTO rawMaterialType);
	void delete(int id);
	List<RawMaterialTypeDTO> find(RawMaterialTypeDTO materialType);
}
