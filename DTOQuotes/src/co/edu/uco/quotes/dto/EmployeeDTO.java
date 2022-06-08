package co.edu.uco.quotes.dto;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;

public class EmployeeDTO {
	
	private int id;
	private String name;
	private IdTypeDTO idType;
	private String idNumber;
	private EmployeeTypeDTO employeeType;
	private EmployeeStatusDTO employeeStatus;
	private CampusDTO campus;
	
	
	
	
	
	public EmployeeDTO(int id, String name, IdTypeDTO idType, String idNumber, EmployeeTypeDTO empleType,
			EmployeeStatusDTO employeeStatus, CampusDTO campus) {
		super();
		
		setCampus(campus);
		setEmpleType(empleType);
		setEmployeeStatus(employeeStatus);
		setId(id);
		setIdNumber(idNumber);
		setIdType(idType);
		setName(name);
	}
	
	public EmployeeDTO() {
		super();
		
		setCampus(new CampusDTO());
		setEmpleType(new EmployeeTypeDTO());
		setEmployeeStatus(new EmployeeStatusDTO());
		setIdNumber(UtilText.EMPTY);
		setIdType(new IdTypeDTO());
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
	
	public EmployeeStatusDTO getEmployeeStatus() {
		return employeeStatus;
	}
	public void setEmployeeStatus(EmployeeStatusDTO employeeStatus) {
		this.employeeStatus = UtilObject.getUtilObject().getDefault(employeeStatus, new EmployeeStatusDTO());
	}
	public CampusDTO getCampus() {
		return campus;
	}
	public void setCampus(CampusDTO campus) {
		this.campus = UtilObject.getUtilObject().getDefault(campus, new CampusDTO());
	}
	public EmployeeTypeDTO getEmployeeType() {
		return employeeType;
	}
	public void setEmpleType(EmployeeTypeDTO empleType) {
		this.employeeType = UtilObject.getUtilObject().getDefault(empleType, new EmployeeTypeDTO());
	}
	
	
	
	
}
