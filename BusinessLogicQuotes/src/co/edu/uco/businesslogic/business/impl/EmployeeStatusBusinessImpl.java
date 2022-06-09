package co.edu.uco.businesslogic.business.impl;

import java.util.List;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.quotes.businesslogic.business.EmployeeStatusBusiness;
import co.edu.uco.quotes.crosscutting.exception.QuotesException;
import co.edu.uco.quotes.data.factory.DAOFactory;
import co.edu.uco.quotes.dto.EmployeeDTO;
import co.edu.uco.quotes.dto.EmployeeStatusDTO;


public class EmployeeStatusBusinessImpl implements EmployeeStatusBusiness {
	
	private DAOFactory daoFactory;
	
	public EmployeeStatusBusinessImpl(DAOFactory daoFactory) {
		if (UtilObject.getUtilObject().isNull(daoFactory)) {
			throw QuotesException.buildTechnicalBussinessLogicException(
					"It's not possible to create a EmployeeStatusBusinessImpl when the DAOFactory is null");
		}
		this.daoFactory = daoFactory;
	}

	@Override
	public void create(EmployeeStatusDTO dto) {
		validateEmployeeStatusDeosNotExistWithSameName(dto);
		daoFactory.getEmployeeStatusDAO().create(dto);
	}
	
	private void validateEmployeeStatusDeosNotExistWithSameName(EmployeeStatusDTO dto) {
		EmployeeStatusDTO dtoValidator = new EmployeeStatusDTO();
		dtoValidator.setName(dto.getName());
		
		List<EmployeeStatusDTO> list = daoFactory.getEmployeeStatusDAO().find(dtoValidator);
		
		if(!list.isEmpty()) {
			var message = "An employeeStatus with the same name already exist";
			throw QuotesException.buildBussinessLogicException(message);
		}
		
	}
	
	private void validateIfExistId(EmployeeStatusDTO dto) {
		EmployeeStatusDTO justId = new EmployeeStatusDTO(dto.getId(), "");
		List<EmployeeStatusDTO> existingEmployeeStatus = daoFactory.getEmployeeStatusDAO().find(justId);
		if(existingEmployeeStatus.isEmpty()) {
			throw QuotesException.buildBussinessLogicException("employeeStatus to not found");
		}
		
	}
	
	private void validateIfIsUsed(EmployeeStatusDTO dto) {

		EmployeeDTO employee = new EmployeeDTO();
		employee.setEmployeeStatus(dto);

		
		if(!daoFactory.getEmployeeDAO().find(employee).isEmpty()) {
		throw QuotesException.buildBussinessLogicException("employeeStatus is associated whit a employeed");
		}
	}
	
	public void validateDuplicated(EmployeeStatusDTO dto) {
		EmployeeStatusDTO dtoValidator = new EmployeeStatusDTO();
		dtoValidator.setName(dto.getName());
		
		List<EmployeeStatusDTO> list = daoFactory.getEmployeeStatusDAO().find(dtoValidator);
		
		if(!list.isEmpty() && dto.getId() != list.get(0).getId()) {
			var message = "An employeeStatus with the same name already exist";
			throw QuotesException.buildBussinessLogicException(message);
		}		
	}

	@Override
	public void update(EmployeeStatusDTO dto) {
		validateIfExistId(dto);
		validateDuplicated(dto);
		daoFactory.getEmployeeStatusDAO().update(dto);
		
	}

	@Override
	public void delete(int id) {
		EmployeeStatusDTO dto = new EmployeeStatusDTO();
		dto.setId(id);
		validateIfExistId(dto);
		validateIfIsUsed(dto);
		daoFactory.getEmployeeStatusDAO().delete(id);

	}

	@Override
	public List<EmployeeStatusDTO> find(EmployeeStatusDTO dto) {	
		return daoFactory.getEmployeeStatusDAO().find(dto);
	}

}
