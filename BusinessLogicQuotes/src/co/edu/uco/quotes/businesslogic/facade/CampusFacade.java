package co.edu.uco.quotes.businesslogic.facade;

import java.util.List;

import co.edu.uco.quotes.dto.CampusDTO;

public interface CampusFacade {
	void create(CampusDTO campus);
	void update(CampusDTO campus);
	void delete(int id);
	List<CampusDTO> Find(CampusDTO campus);
}
