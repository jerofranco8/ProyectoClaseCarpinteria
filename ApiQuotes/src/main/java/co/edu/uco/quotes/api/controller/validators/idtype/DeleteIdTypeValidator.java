package co.edu.uco.quotes.api.controller.validators.idtype;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.quotes.api.controller.validators.Validator;
import co.edu.uco.quotes.dto.IdTypeDTO;

public class DeleteIdTypeValidator implements Validator<IdTypeDTO>{
	
	private List<String> validationMessages = new ArrayList<>();

	@Override
	public List<String> validate(IdTypeDTO dto) {
		if (UtilObject.getUtilObject().isNull(dto)) {
			validationMessages.add("Is not possible to validate Id Type data");
		}
		dto.validateId(validationMessages);
		return validationMessages;
	}
}
