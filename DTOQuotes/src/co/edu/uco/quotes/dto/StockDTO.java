package co.edu.uco.quotes.dto;

import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;

public class StockDTO {
	private int id;
	private String name;
	private int unit;
	private RawMaterialDTO rawMaterial;

	public StockDTO() {
		super();
		setName(UtilText.EMPTY);
		setRawMaterial(new RawMaterialDTO());
		setUnit(0);
	}
	public StockDTO(int id, String name, int unit, RawMaterialDTO rawMaterial) {
		super();
		setId(id);
		setName(name);
		setRawMaterial(rawMaterial);
		setUnit(unit);
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

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = (int) UtilNumeric.getUtilNumeric().getDefault(unit);
	}

	public RawMaterialDTO getRawMaterial() {
		return rawMaterial;
	}

	public void setRawMaterial(RawMaterialDTO rawMaterial) {
		this.rawMaterial = UtilObject.getUtilObject().getDefault(rawMaterial, new RawMaterialDTO());
	}
	
}
