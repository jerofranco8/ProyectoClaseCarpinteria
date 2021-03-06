package co.edu.uco.businesslogic.business.impl;

import java.util.List;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.quotes.businesslogic.business.IdTypeBusiness;
import co.edu.uco.quotes.crosscutting.exception.QuotesException;
import co.edu.uco.quotes.data.factory.DAOFactory;
import co.edu.uco.quotes.dto.ClientDTO;
import co.edu.uco.quotes.dto.EmployeeDTO;
import co.edu.uco.quotes.dto.IdTypeDTO;


public class IdTypeBusinessImpl implements IdTypeBusiness {
	
	private DAOFactory daoFactory;
	
	public IdTypeBusinessImpl(DAOFactory daoFactory) {
		if (UtilObject.getUtilObject().isNull(daoFactory)) {
			throw QuotesException.buildTechnicalBussinessLogicException(
					"It's not possible to create a IdTypeBusinessImpl when the DAOFactory is null");
		}
		this.daoFactory = daoFactory;
	}

	@Override
	public void create(IdTypeDTO dto) {
		validateIdTypeDeosNotExistWithSameName(dto);
		daoFactory.getIdTypeDAO().create(dto);
	}
	
	private void validateIdTypeDeosNotExistWithSameName(IdTypeDTO dto) {
		IdTypeDTO dtoValidator = new IdTypeDTO();
		dtoValidator.setName(dto.getName());
		
		List<IdTypeDTO> list = daoFactory.getIdTypeDAO().find(dtoValidator);
		
		if(!list.isEmpty()) {
			var message = "An id type with the same name already exist";
			throw QuotesException.buildBussinessLogicException(message);
		}
		
	}
	
	private void validateIfExistId(IdTypeDTO dto) {
		IdTypeDTO justId = new IdTypeDTO(dto.getId(), "");
		List<IdTypeDTO> existingIdType = daoFactory.getIdTypeDAO().find(justId);
		if(existingIdType.isEmpty()) {
			throw QuotesException.buildBussinessLogicException("id type to not found");
		}
		
	}
	
	private void validateIfIsUsed(IdTypeDTO dto) {
		ClientDTO client = new ClientDTO();
		client.setIdType(dto);
		
		EmployeeDTO employee = new EmployeeDTO();
		employee.setIdType(dto);
		
		if(!daoFactory.getClientDAO().find(client).isEmpty()) {
			throw QuotesException.buildBussinessLogicException("Id type is associated whit a Client");
		}
		
		if(!daoFactory.getEmployeeDAO().find(employee).isEmpty()) {
		throw QuotesException.buildBussinessLogicException("Id type is associated whit a employeed");
		}
	}
	
	public void validateDuplicated(IdTypeDTO dto) {
		IdTypeDTO dtoValidator = new IdTypeDTO();
		dtoValidator.setName(dto.getName());
		
		List<IdTypeDTO> list = daoFactory.getIdTypeDAO().find(dtoValidator);
		
		if(!list.isEmpty() && dto.getId() != list.get(0).getId()) {
			var message = "An id type with the same name already exist";
			throw QuotesException.buildBussinessLogicException(message);
		}		
	}

	@Override
	public void update(IdTypeDTO dto) {
		validateIfExistId(dto);
		validateDuplicated(dto);
		daoFactory.getIdTypeDAO().update(dto);
		
	}

	@Override
	public void delete(int id) {
		IdTypeDTO dto = new IdTypeDTO();
		dto.setId(id);
		validateIfExistId(dto);
		validateIfIsUsed(dto);
		daoFactory.getIdTypeDAO().delete(id);

	}

	@Override
	public List<IdTypeDTO> find(IdTypeDTO dto) {	
		return daoFactory.getIdTypeDAO().find(dto);
	}

}
