package co.edu.uco.businesslogic.business.impl;

import java.util.List;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.quotes.businesslogic.business.EmployeeTypeBusiness;
import co.edu.uco.quotes.crosscutting.exception.QuotesException;
import co.edu.uco.quotes.data.factory.DAOFactory;
import co.edu.uco.quotes.dto.EmployeeDTO;
import co.edu.uco.quotes.dto.EmployeeTypeDTO;

public class EmployeeTypeBusinessImpl  implements EmployeeTypeBusiness {
	
	private DAOFactory daoFactory;
	
	public EmployeeTypeBusinessImpl(DAOFactory daoFactory) {
		if (UtilObject.getUtilObject().isNull(daoFactory)) {
			throw QuotesException.buildTechnicalBussinessLogicException(
					"It's not possible to create a EmployeeTypeBusinessImpl when the DAOFactory is null");
		}
		this.daoFactory = daoFactory;
	}

	@Override
	public void create(EmployeeTypeDTO dto) {
		validateEmployeeTypeDeosNotExistWithSameName(dto);
		validateEmployeeTypeDeosNotExistWithSameFunction(dto);
		
		daoFactory.getEmployeeTypeDAO().create(dto);
	}
	
	private void validateEmployeeTypeDeosNotExistWithSameName(EmployeeTypeDTO dto) {
		EmployeeTypeDTO dtoValidator = new EmployeeTypeDTO();
		dtoValidator.setName(dto.getName());
		
		List<EmployeeTypeDTO> list = daoFactory.getEmployeeTypeDAO().find(dtoValidator);
		
		if(!list.isEmpty()) {
			var message = "An Employee type with the same name already exist";
			throw QuotesException.buildBussinessLogicException(message);
		}
		
	}
		
	private void validateEmployeeTypeDeosNotExistWithSameFunction(EmployeeTypeDTO dto) {
		EmployeeTypeDTO dtoValidator = new EmployeeTypeDTO();
		dtoValidator.setFunction(dto.getFunction());
		
		List<EmployeeTypeDTO> list = daoFactory.getEmployeeTypeDAO().find(dtoValidator);
		
		if(!list.isEmpty()) {
			var message = "An Employee type with the same function already exist";
			throw QuotesException.buildBussinessLogicException(message);
		}
		
	}
	
	private void validateIfExistId(EmployeeTypeDTO dto) {
		EmployeeTypeDTO justId = new EmployeeTypeDTO();
		justId.setId(dto.getId());
		List<EmployeeTypeDTO> existingEmployeeType = daoFactory.getEmployeeTypeDAO().find(justId);
		if(existingEmployeeType.isEmpty()) {
			throw QuotesException.buildBussinessLogicException("Employee type to not found");
		}
		
	}
	
	private void validateIfIsUsed(EmployeeTypeDTO dto) {	
		EmployeeDTO employee = new EmployeeDTO();
		employee.setEmployeeType(dto);
		
		if(!daoFactory.getEmployeeDAO().find(employee).isEmpty()) {
		throw QuotesException.buildBussinessLogicException("Employee type is associated whit a employeed");
		}
	}
	
	public void validateDuplicated(EmployeeTypeDTO dto) {
		EmployeeTypeDTO dtoValidator = new EmployeeTypeDTO();
		dtoValidator.setName(dto.getName());
		dtoValidator.setFunction(dto.getFunction());
		
		List<EmployeeTypeDTO> list = daoFactory.getEmployeeTypeDAO().find(dtoValidator);
		
		if(!list.isEmpty() && dto.getId() != list.get(0).getId()) {
			var message = "An Employee type with the same name o function already exist";
			throw QuotesException.buildBussinessLogicException(message);
		}		
	}

	@Override
	public void update(EmployeeTypeDTO dto) {
		validateIfExistId(dto);
		validateDuplicated(dto);
		daoFactory.getEmployeeTypeDAO().update(dto);
		
	}

	@Override
	public void delete(int id) {
		EmployeeTypeDTO dto = new EmployeeTypeDTO();
		dto.setId(id);
		validateIfExistId(dto);
		validateIfIsUsed(dto);
		daoFactory.getEmployeeTypeDAO().delete(id);

	}

	@Override
	public List<EmployeeTypeDTO> find(EmployeeTypeDTO dto) {	
		return daoFactory.getEmployeeTypeDAO().find(dto);
	}

}

