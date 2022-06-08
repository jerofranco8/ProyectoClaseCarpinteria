package co.edu.uco.quotes.businesslogic.facade;

import java.util.List;

import co.edu.uco.quotes.dto.EmployeeDTO;

public interface EmployeeFacade {
	void create(EmployeeDTO employee);
	void update(EmployeeDTO employee);
	void delete(int id);
	List<EmployeeDTO> find(EmployeeDTO employee);
}
