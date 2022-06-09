package co.edu.uco.quotes.dto;

import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;

public class RawMaterialDTO {
	private int id;
	private String name;
	private String details;
	private int value;
	private RawMaterialTypeDTO rawMaterialType;
	
	
	
	public RawMaterialDTO(int id, String name, String details, int value, RawMaterialTypeDTO rawMaterialType) {
		super();
		setDetails(details);
		setId(id);
		setName(name);
		setRawMaterialType(rawMaterialType);
		setValue(value);
		}
	
	public RawMaterialDTO() {
		super();
		setDetails(UtilText.EMPTY);
		setName(UtilText.EMPTY);
		setRawMaterialType(new RawMaterialTypeDTO());
		setValue(0);
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
		this.name = UtilText.getDefault(name);
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = UtilText.getDefault(details);
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value =(int) UtilNumeric.getUtilNumeric().getDefault(value);
	}

	public RawMaterialTypeDTO getRawMaterialType() {
		return rawMaterialType;
	}

	public void setRawMaterialType(RawMaterialTypeDTO rawMaterialType) {
		this.rawMaterialType = UtilObject.getUtilObject().getDefault(rawMaterialType, new RawMaterialTypeDTO());
	}
	
	

	
}
