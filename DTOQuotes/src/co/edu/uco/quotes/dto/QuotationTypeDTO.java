package co.edu.uco.quotes.dto;

import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.text.UtilText;

public class QuotationTypeDTO {
	private int id;
	private String name;
	private String datil;
	private int cabinetMeterValue;
	
	
	
	
	public QuotationTypeDTO(int id, String name, String datil, int cabinetMeterValue) {
		super();
		setCabinetMeterValue(cabinetMeterValue);
		setDatil(datil);
		setId(id);
		setName(name);
		}
	
	public QuotationTypeDTO() {
		super();
		setCabinetMeterValue(0);
		setDatil(UtilText.EMPTY);
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
	public String getDatil() {
		return datil;
	}
	public void setDatil(String datil) {
		this.datil = UtilText.getDefault(datil);
	}
	public int getCabinetMeterValue() {
		return cabinetMeterValue;
	}
	public void setCabinetMeterValue(int cabinetMeterValue) {
		this.cabinetMeterValue = (int)UtilNumeric.getUtilNumeric().getDefault(cabinetMeterValue);
	}
	
	
}
