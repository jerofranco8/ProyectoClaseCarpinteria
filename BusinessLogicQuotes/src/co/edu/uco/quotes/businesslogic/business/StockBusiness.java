package co.edu.uco.quotes.businesslogic.business;

import java.util.List;

import co.edu.uco.quotes.dto.StockDTO;

public interface StockBusiness {
	void create(StockDTO stock);
	void update(StockDTO stock);
	void delete(int id);
	List<StockDTO> find (StockDTO stock);
}
