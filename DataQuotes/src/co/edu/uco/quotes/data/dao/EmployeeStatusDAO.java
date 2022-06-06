package co.edu.uco.quotes.data.dao;

import java.util.List;

import co.edu.uco.quotes.dto.EmployeeStatusDTO;

public interface EmployeeStatusDAO {
	void create(EmployeeStatusDTO employeeStatus);
	void update(EmployeeStatusDTO employeeStatus);
	void delete(int id);
	List<EmployeeStatusDTO> find(EmployeeStatusDTO employeeStatus);
}
