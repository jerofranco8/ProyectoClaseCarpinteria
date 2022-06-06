package co.edu.uco.quotes.data.dao;

import java.util.List;

import co.edu.uco.quotes.dto.CityDTO;


public interface CityDAO {
	void create(CityDTO city);
	void update(CityDTO city);
	void delete(int id);
	List<CityDTO> find(CityDTO city);
}
