package co.edu.uco.quotes.dto;


import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;

public class CampusDTO {
	
	private int id;
	private String name;
	private CityDTO city;
	private String direction;


	public CampusDTO(int id, String name, CityDTO city, String direction
			) {
		super();
		setId(id);
		setName(name);
		setCity(city);

		setDirection(direction);

	}
	
	public CampusDTO() {
		super();
		setName(UtilText.EMPTY);
		setCity(new CityDTO());

		setDirection(UtilText.EMPTY);

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

	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = UtilText.getDefault(direction);
	}

	
	
	
	
	

}
