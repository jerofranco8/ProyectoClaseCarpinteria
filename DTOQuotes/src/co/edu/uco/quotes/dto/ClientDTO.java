package co.edu.uco.quotes.dto;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;

public class ClientDTO {
	
	private int id;
	private String name;
	private IdTypeDTO idType;
	private String idNumber;

	
	
	
	public ClientDTO() {
		super();
		setName(UtilText.EMPTY);
		setIdNumber(UtilText.EMPTY);
		setIdType(new IdTypeDTO());
		;
	}
	
	public ClientDTO(int id, String name, IdTypeDTO idType, String idNumber) {
		super();
		setId(id);
		setName(name);
		setIdNumber(idNumber);
		setIdType(idType);
	
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
	public IdTypeDTO getIdType() {
		return idType;
	}
	public void setIdType(IdTypeDTO idType) {
		this.idType = UtilObject.getUtilObject().getDefault(idType, new IdTypeDTO());
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = UtilText.getDefault(idNumber);
	}
	
	
	
	
}
