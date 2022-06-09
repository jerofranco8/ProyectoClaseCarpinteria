package co.edu.uco.quotes.businesslogic.facade.impl;

import java.util.List;


import co.edu.uco.businesslogic.business.impl.RawMaterialTypeBusinessImpl;
import co.edu.uco.quotes.businesslogic.business.RawMaterialTypeBusiness;
import co.edu.uco.quotes.businesslogic.facade.RawMaterialTypeFacade;
import co.edu.uco.quotes.crosscutting.exception.QuotesException;
import co.edu.uco.quotes.data.factory.DAOFactory;
import co.edu.uco.quotes.dto.RawMaterialTypeDTO;

public class RawMaterialTypeFacadeImpl implements RawMaterialTypeFacade {

	@Override
	public void create(RawMaterialTypeDTO dto) {
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		
		try {
			
			daoFactory.initTransaction();
			
			RawMaterialTypeBusiness rawMaterialType = new RawMaterialTypeBusinessImpl(daoFactory);
			rawMaterialType.create(dto);
			
			daoFactory.commitTransaction();
			
		} catch (QuotesException exception){
			daoFactory.rollbackTransaction();
			throw exception;
			
		} catch (Exception exception){
			daoFactory.rollbackTransaction();
			String message = "There was an unexpected problem trying to create the new rawMaterialType on 'create' method of rawmaterialFacadeImpl";
			throw QuotesException.buildTechnicalBussinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
		}
	}

	@Override
	public void update(RawMaterialTypeDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			
			daoFactory.initTransaction();
			RawMaterialTypeBusiness rawMaterialType = new RawMaterialTypeBusinessImpl(daoFactory);
			rawMaterialType.update(dto);
			
			daoFactory.commitTransaction();
			
		} catch (QuotesException exception){
			daoFactory.rollbackTransaction();
			throw exception;
			
		} catch (Exception exception){
			daoFactory.rollbackTransaction();
			var message = "There was an unexpected problem trying to update the rawMaterialType on 'update' method of rawmaterialFacadeImpl";
			throw QuotesException.buildTechnicalBussinessLogicException(message);
		} finally {
			
			daoFactory.closeConnection();
			
		}
		
		
	}

	@Override
	public void delete(int id) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			
			daoFactory.initTransaction();
			RawMaterialTypeBusiness rawMaterialType = new RawMaterialTypeBusinessImpl(daoFactory);
			rawMaterialType.delete(id);
			
			daoFactory.commitTransaction();
			
		} catch (QuotesException exception){
			daoFactory.rollbackTransaction();
			throw exception;
			
		} catch (Exception exception){
			daoFactory.rollbackTransaction();
			var message = "There was an unexpected problem trying to delete the rawMaterialType on 'update' method of rawmaterialFacadeImpl";
			throw QuotesException.buildTechnicalBussinessLogicException(message);
		} finally {
			
			daoFactory.closeConnection();
			
		}
		
		
		
	}

	@Override
	public List<RawMaterialTypeDTO> find(RawMaterialTypeDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			
			daoFactory.initTransaction();
			
			RawMaterialTypeBusiness rawMaterialType = new RawMaterialTypeBusinessImpl(daoFactory);
			return rawMaterialType.find(dto);
			
		} catch (QuotesException exception){
			throw exception;
			
		} catch (Exception exception){
			var message = "There was an unexpected problem trying to find the new rawmaterial on 'find' method of rawmaterialFacadeImpl";
			throw QuotesException.buildTechnicalBussinessLogicException(message);
		} finally {
			
			daoFactory.closeConnection();
			
		}
	}

}
