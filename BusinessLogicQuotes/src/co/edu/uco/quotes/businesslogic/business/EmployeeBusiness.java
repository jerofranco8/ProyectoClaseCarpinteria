package co.edu.uco.quotes.businesslogic.business;

import java.util.List;

import co.edu.uco.quotes.dto.EmployeeDTO;

public interface EmployeeBusiness {
	void create(EmployeeDTO employee);
	void update(EmployeeDTO employee);
	void delete(int id);
	List<EmployeeDTO> find(EmployeeDTO employee);
}
