package co.edu.uco.quotes.api.controller.validators.employeestatus;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.quotes.api.controller.validators.Validator;
import co.edu.uco.quotes.dto.EmployeeStatusDTO;

public class UpdateEmployeeStatusValidator implements Validator<EmployeeStatusDTO>{
	
	private List<String> validationMessages = new ArrayList<>();

	@Override
	public List<String> validate(EmployeeStatusDTO dto) {
		if (UtilObject.getUtilObject().isNull(dto)) {
			validationMessages.add("Is not possible to validate EmployeeStatu data");
		}
		dto.validateName(validationMessages);
		dto.validateId(validationMessages);
		return validationMessages;
	}
}