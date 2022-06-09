package co.edu.uco.quotes.api.controller.validators.employeetype;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.quotes.api.controller.validators.Validator;
import co.edu.uco.quotes.dto.EmployeeTypeDTO;

public class FindEmployeeTypeValidator implements Validator<EmployeeTypeDTO> {
private List<String> validationMessages = new ArrayList<>(); 
	
	@Override
	public List<String> validate(EmployeeTypeDTO dto) {
		if(UtilObject.getUtilObject().isNull(dto)) {
			validationMessages.add("Is not possible to validate Empleoyee type data");
		}
		
		dto.validateId(validationMessages);
		return validationMessages;
	}
}