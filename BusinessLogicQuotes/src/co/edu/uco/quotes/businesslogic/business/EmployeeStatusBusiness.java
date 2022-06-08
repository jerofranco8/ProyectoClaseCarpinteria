package co.edu.uco.quotes.businesslogic.business;

import java.util.List;

import co.edu.uco.quotes.dto.EmployeeStatusDTO;

public interface EmployeeStatusBusiness {
	void create(EmployeeStatusDTO employeeStatus);
	void update(EmployeeStatusDTO employeeStatus);
	void delete(int id);
	List<EmployeeStatusDTO> find(EmployeeStatusDTO employeeStatus);
}
