package co.edu.uco.quotes.businesslogic.business;

import java.util.List;

import co.edu.uco.quotes.dto.QuotationTypeDTO;

public interface QuotationTypeBusiness {
	void create(QuotationTypeDTO quotationType);
	void update(QuotationTypeDTO quotationType);
	void delete(int id);
	List<QuotationTypeDTO> find(QuotationTypeDTO quotationType);
}
