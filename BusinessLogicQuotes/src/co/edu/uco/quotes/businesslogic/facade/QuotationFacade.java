package co.edu.uco.quotes.businesslogic.facade;

import java.util.List;

import co.edu.uco.quotes.dto.QuotationDTO;

public interface QuotationFacade {
	void create(QuotationDTO quotation);
	void update(QuotationDTO quotation);
	void delete(int id);
	List<QuotationDTO> find(QuotationDTO quotation);
}
