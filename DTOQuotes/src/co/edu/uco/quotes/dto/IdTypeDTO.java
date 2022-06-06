package co.edu.uco.quotes.dto;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;

public class IdTypeDTO {

	private int id;
	private String name;

	public IdTypeDTO(int id, String name) {
		super();
		setId(id);
		setName(name);
	}

	public IdTypeDTO() {
		super();
		setName(UtilText.EMPTY);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = UtilText.trim(name);
	}

	public void validateName(List<String> validationMessages) {

		validationMessages = UtilObject.getUtilObject().getDefault(validationMessages, new ArrayList<>());

		if (UtilText.isEmpty(getName())) {

			validationMessages.add("Name of id type is required");

		} else if (UtilText.getDefault(getName()).length() > 50) {

			validationMessages.add("The lenght of the name of id type must be less than 50 charecters");

		} else if (!UtilText.getDefault(getName()).matches("^[a-zA-ZÒ—·¡È…ÌÕÛ”˙⁄ ]*$")) {
			validationMessages.add("The name of id type has not valid characters");
		}
	}
	
	public void validateId(List<String> validationMessages) {
		validationMessages = UtilObject.getUtilObject().getDefault(validationMessages, new ArrayList<>());
		if(!UtilNumeric.getUtilNumeric().isGreatherThan(getId(), 0)) {
			validationMessages.add("The id must be greather than zero");
		}
		
	}

}
