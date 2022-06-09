package co.edu.uco.businesslogic.business.impl;

import java.util.List;

import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.quotes.businesslogic.business.RawMaterialTypeBusiness;
import co.edu.uco.quotes.crosscutting.exception.QuotesException;
import co.edu.uco.quotes.data.factory.DAOFactory;
import co.edu.uco.quotes.dto.RawMaterialTypeDTO;
import co.edu.uco.quotes.dto.RawMaterialDTO;


public class RawMaterialTypeBusinessImpl implements RawMaterialTypeBusiness{

	private DAOFactory daoFactory;
	
	public RawMaterialTypeBusinessImpl(DAOFactory daoFactory) {
		if (UtilObject.getUtilObject().isNull(daoFactory)) {
			throw QuotesException.buildTechnicalBussinessLogicException(
					"It's not possible to create a RawMaterialTypeBusinessImpl when the DAOFactory is null");
		}
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void create(RawMaterialTypeDTO dto) {
		validateRawMaterialTypeDeosNotExistWithSameName(dto);
		daoFactory.getMaterialTypeDAO().create(dto);
	}
	
	private void validateRawMaterialTypeDeosNotExistWithSameName(RawMaterialTypeDTO dto) {
		RawMaterialTypeDTO dtoValidator = new RawMaterialTypeDTO();
		dtoValidator.setName(dto.getName());
		
		List<RawMaterialTypeDTO> list = daoFactory.getMaterialTypeDAO().find(dtoValidator);
		
		if(!list.isEmpty()) {
			var message = "An id type with the same name already exist";
			throw QuotesException.buildBussinessLogicException(message);
		}
		
	}
	
	private void validateIfExistId(RawMaterialTypeDTO dto) {
		RawMaterialTypeDTO justId = new RawMaterialTypeDTO(dto.getId(), "");
		List<RawMaterialTypeDTO> existingRawMaterialType = daoFactory.getMaterialTypeDAO().find(justId);
		if(existingRawMaterialType.isEmpty()) {
			throw QuotesException.buildBussinessLogicException("rawMaterial to not found");
		}
		
	}
	
	private void validateIfIsUsed(RawMaterialTypeDTO dto) {
		
		RawMaterialDTO rawMaterial = new RawMaterialDTO();
		rawMaterial.setRawMaterialType(dto);
		
		if(!daoFactory.getMaterialDAO().find(rawMaterial).isEmpty()) {
			throw QuotesException.buildBussinessLogicException("Raw Material Type is associated whit a rawMaterial");
		}

	}
	
	public void validateDuplicated(RawMaterialTypeDTO dto) {
		RawMaterialTypeDTO dtoValidator = new RawMaterialTypeDTO();
		dtoValidator.setName(dto.getName());
		
		List<RawMaterialTypeDTO> list = daoFactory.getMaterialTypeDAO().find(dtoValidator);
		
		if(!list.isEmpty() && dto.getId() != list.get(0).getId()) {
			var message = "An id type with the same name already exist";
			throw QuotesException.buildBussinessLogicException(message);
		}	
	}

	@Override
	public void update(RawMaterialTypeDTO rawMaterial) {
		validateIfExistId(rawMaterial);
		validateDuplicated(rawMaterial);

		daoFactory.getMaterialTypeDAO().update(rawMaterial);
	}

	@Override
	public void delete(int id) {
		RawMaterialTypeDTO dto = new RawMaterialTypeDTO();
		dto.setId(id);
		
		validateIfExistId(dto);
		validateIfIsUsed(dto);
		
		daoFactory.getMaterialTypeDAO().delete(id);
	}

	@Override
	public List<RawMaterialTypeDTO> find(RawMaterialTypeDTO material) {

		return daoFactory.getMaterialTypeDAO().find(material);
	}
	
	
}
