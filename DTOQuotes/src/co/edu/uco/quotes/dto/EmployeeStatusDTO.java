package co.edu.uco.quotes.dto;


import co.edu.uco.crosscutting.util.text.UtilText;

public class EmployeeStatusDTO {
	
	private int id;
	private String name;
	
	
	public EmployeeStatusDTO() {
		super();
		setName(UtilText.EMPTY);
	}
	
	public EmployeeStatusDTO(int id, String name) {
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
		return name;
	}
	public void setName(String name) {
		this.name = UtilText.EMPTY;
	}
	
	
	

}
