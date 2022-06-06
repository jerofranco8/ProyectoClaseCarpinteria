package co.edu.uco.quotes.dto;

import co.edu.uco.crosscutting.util.text.UtilText;

public class RawMaterialTypeDTO {
	private int id;
	private String name;

	public RawMaterialTypeDTO(int id, String name) {
		super();
		setId(id);
		setName(name);
	}

	public RawMaterialTypeDTO() {
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
		this.name = UtilText.getDefault(name);
	}
}
