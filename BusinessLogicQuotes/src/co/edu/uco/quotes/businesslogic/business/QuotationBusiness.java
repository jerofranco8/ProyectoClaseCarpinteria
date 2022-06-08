package co.edu.uco.quotes.businesslogic.business;

import java.util.List;

import co.edu.uco.quotes.dto.QuotationDTO;

public interface QuotationBusiness {
	void create(QuotationDTO quotation);
	void update(QuotationDTO quotation);
	void delete(int id);
	List<QuotationDTO> find(QuotationDTO quotation);
}
