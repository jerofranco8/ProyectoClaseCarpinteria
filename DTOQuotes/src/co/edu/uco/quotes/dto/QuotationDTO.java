package co.edu.uco.quotes.dto;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;

public class QuotationDTO {
	private int id;
	private String name;
	private int upperCabinetMeter;
	private int lowerCabinetMeter;
	private int graniteMeter;
	private int priceQuotation;
	private QuotationTypeDTO quotationType;
	private CampusDTO campus;
	private ClientDTO client;
	private List<RawMaterialDTO> rawMaterials;
	
	
	
	
	
	public QuotationDTO(int id, String name, int upperCabinetMeter, int lowerCabinetMeter, int graniteMeter,
			int priceQuotation, QuotationTypeDTO quotationType, CampusDTO campus, ClientDTO client,
			List<RawMaterialDTO> rawMaterials) {
		super();
		setCampus(campus);
		setClient(client);
		setGraniteMeter(graniteMeter);
		setId(id);
		setLowerCabinetMeter(lowerCabinetMeter);
		setName(name);
		setPriceQuotation(priceQuotation);
		setQuotationType(quotationType);
		setRawMaterials(rawMaterials);
		setUpperCabinetMeter(upperCabinetMeter);
	}
	
	public QuotationDTO() {
		super();
		setCampus(new CampusDTO());
		setClient(new ClientDTO());
		setGraniteMeter(0);
		setLowerCabinetMeter(0);
		setName(UtilText.EMPTY);
		setPriceQuotation(0);
		setQuotationType(new QuotationTypeDTO());
		setRawMaterials(new ArrayList<RawMaterialDTO>());
		setUpperCabinetMeter(0);
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
	public int getUpperCabinetMeter() {
		return upperCabinetMeter;
	}
	public void setUpperCabinetMeter(int upperCabinetMeter) {
		this.upperCabinetMeter = (int) UtilNumeric.getUtilNumeric().getDefault(upperCabinetMeter);
	}
	public int getLowerCabinetMeter() {
		return lowerCabinetMeter;
	}
	public void setLowerCabinetMeter(int lowerCabinetMeter) {
		this.lowerCabinetMeter = (int) UtilNumeric.getUtilNumeric().getDefault(lowerCabinetMeter);
	}
	public int getGraniteMeter() {
		return graniteMeter;
	}
	public void setGraniteMeter(int graniteMeter) {
		this.graniteMeter = (int) UtilNumeric.getUtilNumeric().getDefault(graniteMeter);
	}
	public int getPriceQuotation() {
		return priceQuotation;
	}
	public void setPriceQuotation(int priceQuotation) {
		this.priceQuotation = (int) UtilNumeric.getUtilNumeric().getDefault(priceQuotation);
	}
	public QuotationTypeDTO getQuotationType() {
		return quotationType;
	}
	public void setQuotationType(QuotationTypeDTO quotationType) {
		this.quotationType = UtilObject.getUtilObject().getDefault(quotationType, new QuotationTypeDTO());
	}
	public CampusDTO getCampus() {
		return campus;
	}
	public void setCampus(CampusDTO campus) {
		this.campus = UtilObject.getUtilObject().getDefault(campus, new CampusDTO());
	}
	public ClientDTO getClient() {
		return client;
	}
	public void setClient(ClientDTO client) {
		this.client = UtilObject.getUtilObject().getDefault(client, new ClientDTO());
	}
	public List<RawMaterialDTO> getRawMaterials() {
		return rawMaterials;
	}
	public void setRawMaterials(List<RawMaterialDTO> rawMaterials) {
		this.rawMaterials = UtilObject.getUtilObject().getDefault(rawMaterials, new ArrayList<RawMaterialDTO>());
	}
	
	
}
