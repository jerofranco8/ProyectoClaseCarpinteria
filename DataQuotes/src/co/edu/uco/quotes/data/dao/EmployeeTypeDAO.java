package co.edu.uco.quotes.data.dao;

import java.util.List;

import co.edu.uco.quotes.dto.EmployeeTypeDTO;

public interface EmployeeTypeDAO {
	void create(EmployeeTypeDTO employeeType);
	void update(EmployeeTypeDTO employeeType);
	void delete(int id);
	List<EmployeeTypeDTO> find(EmployeeTypeDTO employeeType);
}
