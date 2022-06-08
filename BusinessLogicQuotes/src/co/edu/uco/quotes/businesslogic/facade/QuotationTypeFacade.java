package co.edu.uco.quotes.businesslogic.facade;

import java.util.List;


import co.edu.uco.quotes.dto.QuotationTypeDTO;

public interface QuotationTypeFacade {
	void create(QuotationTypeDTO quotationType);
	void update(QuotationTypeDTO quotationType);
	void delete(int id);
	List<QuotationTypeDTO> find(QuotationTypeDTO quotationType);
}
