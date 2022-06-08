package co.edu.uco.quotes.businesslogic.facade;

import java.util.List;

import co.edu.uco.quotes.dto.CityDTO;

public interface CityFacade {
	void create(CityDTO city);
	void update(CityDTO city);
	void delete(int id);
	List<CityDTO> find(CityDTO city);
}
