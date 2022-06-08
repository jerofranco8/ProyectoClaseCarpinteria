package co.edu.uco.quotes.businesslogic.business;

import java.util.List;

import co.edu.uco.quotes.dto.CityDTO;

public interface CityBusiness {
	void create(CityDTO city);
	void update(CityDTO city);
	void delete(int id);
	List<CityDTO> find(CityDTO city);
}
