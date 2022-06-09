package co.edu.uco.quotes.api.controller.validators.rawmaterialtype;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.quotes.api.controller.validators.Validator;
import co.edu.uco.quotes.dto.RawMaterialTypeDTO;

public class CreatedRawMaterialTypeValidator implements Validator<RawMaterialTypeDTO>{

	private List<String> validationMessages = new ArrayList<>();

	@Override
	public List<String> validate(RawMaterialTypeDTO dto) {
		if (UtilObject.getUtilObject().isNull(dto)) {
			validationMessages.add("Is not possible to validate RawMaterialType data");
		}
		dto.validateName(validationMessages);
		return validationMessages;
	}

}
