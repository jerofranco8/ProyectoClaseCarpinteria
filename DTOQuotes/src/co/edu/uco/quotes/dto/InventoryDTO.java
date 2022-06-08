package co.edu.uco.quotes.dto;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;

public class InventoryDTO {
	private int id;
	private String name;
	private CampusDTO campus;


	public InventoryDTO() {
		super();
		setName(UtilText.EMPTY);
		setCampus(new CampusDTO());
	}
	public InventoryDTO(int id, String name, CampusDTO campus) {
		super();
		setId(id);
		setName(name);

	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return UtilText.getDefault(name);
	}
	public void setName(String name) {
		this.name = name;
	}
	public CampusDTO getCampus() {
		return campus;
	}
	public void setCampus(CampusDTO campus) {
		this.campus = UtilObject.getUtilObject().getDefault(campus, new CampusDTO());
	}
	
	

	
	
}
