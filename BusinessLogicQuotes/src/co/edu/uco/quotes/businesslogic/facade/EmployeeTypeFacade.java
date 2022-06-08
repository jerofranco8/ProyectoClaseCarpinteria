package co.edu.uco.quotes.businesslogic.facade;

import java.util.List;

import co.edu.uco.quotes.dto.EmployeeTypeDTO;

public interface EmployeeTypeFacade {
	void create(EmployeeTypeDTO employeeType);
	void update(EmployeeTypeDTO employeeType);
	void delete(int id);
	List<EmployeeTypeDTO> find(EmployeeTypeDTO employeeType);
}
