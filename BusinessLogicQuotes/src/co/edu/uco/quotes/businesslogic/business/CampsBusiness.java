package co.edu.uco.quotes.businesslogic.business;

import java.util.List;

import co.edu.uco.quotes.dto.CampusDTO;

public interface CampsBusiness {
	void create(CampusDTO campus);
	void update(CampusDTO campus);
	void delete(int id);
	List<CampusDTO> Find(CampusDTO campus);
}
