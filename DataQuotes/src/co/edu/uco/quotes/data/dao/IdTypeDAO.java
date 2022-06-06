package co.edu.uco.quotes.data.dao;

import java.util.List;

import co.edu.uco.quotes.dto.IdTypeDTO;

public interface IdTypeDAO {
	
	void create(IdTypeDTO idType);
	void update(IdTypeDTO idType);
	void delete(int id);
	List<IdTypeDTO> find(IdTypeDTO idType);

}