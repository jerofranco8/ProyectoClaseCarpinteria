package co.edu.uco.quotes.data.dao;

import java.util.List;

import co.edu.uco.quotes.dto.QuotationTypeDTO;

public interface QuotationTypeDAO {
	void create(QuotationTypeDTO quotationType);
	void update(QuotationTypeDTO quotationType);
	void delete(int id);
	List<QuotationTypeDTO> find(QuotationTypeDTO quotationType);
}
