package co.edu.uco.quotes.businesslogic.facade.impl;

import java.util.List;

import co.edu.uco.businesslogic.business.impl.IdTypeBusinessImpl;
import co.edu.uco.quotes.businesslogic.business.IdTypeBusiness;
import co.edu.uco.quotes.businesslogic.facade.IdTypeFacade;
import co.edu.uco.quotes.crosscutting.exception.QuotesException;
import co.edu.uco.quotes.data.factory.DAOFactory;
import co.edu.uco.quotes.dto.IdTypeDTO;

public class IdTypeFacadeImpl  implements IdTypeFacade{
	
	
	@Override
	public void create(IdTypeDTO dto) {
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		
		try {
			
			daoFactory.initTransaction();
			
			IdTypeBusiness idTypeBusiness = new IdTypeBusinessImpl(daoFactory);
			idTypeBusiness.create(dto);
			
			daoFactory.commitTransaction();
			
		} catch (QuotesException exception){
			daoFactory.rollbackTransaction();
			throw exception;
			
		} catch (Exception exception){
			daoFactory.rollbackTransaction();
			String message = "There was an unexpected problem trying to create the new IdType on 'create' method of IdTypeFacadeImpl";
			throw QuotesException.buildTechnicalBussinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
		}
		
	}

	@Override
	public void update(IdTypeDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			
			daoFactory.initTransaction();
			
			IdTypeBusiness idTypeBusiness = new IdTypeBusinessImpl(daoFactory);
			idTypeBusiness.update(dto);
			
			daoFactory.commitTransaction();
			
		} catch (QuotesException exception){
			daoFactory.rollbackTransaction();
			throw exception;
			
		} catch (Exception exception){
			daoFactory.rollbackTransaction();
			var message = "There was an unexpected problem trying to update the IdType on 'update' method of IdTypeFacadeImpl";
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
			
			IdTypeBusiness idTypeBusiness = new IdTypeBusinessImpl(daoFactory);
			idTypeBusiness.delete(id);
			
			daoFactory.commitTransaction();
			
		} catch (QuotesException exception){
			daoFactory.rollbackTransaction();
			throw exception;
			
		} catch (Exception exception){
			daoFactory.rollbackTransaction();
			var message = "There was an unexpected problem trying to delete the IdType on 'delete' method of IdTypeFacadeImpl";
			throw QuotesException.buildTechnicalBussinessLogicException(message);
		} finally {
			
			daoFactory.closeConnection();
			
		}
		
		
	}

	@Override
	public List<IdTypeDTO> find(IdTypeDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			
			daoFactory.initTransaction();
			
			IdTypeBusiness idTypeBusiness = new IdTypeBusinessImpl(daoFactory);
			return idTypeBusiness.find(dto);
			
		} catch (QuotesException exception){
			throw exception;
			
		} catch (Exception exception){
			var message = "There was an unexpected problem trying to find the new IdType on 'find' method of IdTypeFacadeImpl";
			throw QuotesException.buildTechnicalBussinessLogicException(message);
		} finally {
			
			daoFactory.closeConnection();
			
		}
	}
}
