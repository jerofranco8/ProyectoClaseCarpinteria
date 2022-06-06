package co.edu.uco.quotes.dto;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;

public class CampusDTO {
	
	private int id;
	private String name;
	private CityDTO city;
	private InventoryDTO inventory;
	private String direction;
	//private List<EmployeeDTO> employees;

	public CampusDTO(int id, String name, CityDTO city, InventoryDTO inventory, String direction
			) {
		super();
		setId(id);
		setName(name);
		setCity(city);
		setInventory(inventory);
		setDirection(direction);
		//setEmployees(employees);
	}
	
	public CampusDTO() {
		super();
		setName(UtilText.EMPTY);
		setCity(new CityDTO());
		setInventory(new InventoryDTO());
		setDirection(UtilText.EMPTY);
		//setEmployees(new ArrayList<EmployeeDTO>());
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
	public CityDTO getCity() {
		return city;
	}
	public void setCity(CityDTO city) {
		this.city = UtilObject.getUtilObject().getDefault(city, new CityDTO());
	}
	public InventoryDTO getInventory() {
		return inventory;
	}
	public void setInventory(InventoryDTO inventory) {
		this.inventory = UtilObject.getUtilObject().getDefault(inventory, new InventoryDTO());
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = UtilText.getDefault(direction);
	}
//	public List<EmployeeDTO> getEmployees() {
//		return employees;
//	}
//	public void setEmployees(List<EmployeeDTO> employees) {
//		this.employees = UtilObject.getUtilObject().getDefault(employees, new ArrayList<EmployeeDTO>());
//	}
	
	
	
	
	

}
