package co.edu.uco.quotes.businesslogic.facade.impl;

import java.util.List;

import co.edu.uco.businesslogic.business.impl.EmployeeTypeBusinessImpl;
import co.edu.uco.quotes.businesslogic.business.EmployeeTypeBusiness;
import co.edu.uco.quotes.businesslogic.facade.EmployeeTypeFacade;
import co.edu.uco.quotes.crosscutting.exception.QuotesException;
import co.edu.uco.quotes.data.factory.DAOFactory;
import co.edu.uco.quotes.dto.EmployeeTypeDTO;

public class EmployeeTypeFacadeImpl implements EmployeeTypeFacade{
	
	
	@Override
	public void create(EmployeeTypeDTO dto) {
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		
		try {
			
			daoFactory.initTransaction();
			
			EmployeeTypeBusiness employeeTypeBusiness = new EmployeeTypeBusinessImpl(daoFactory);
			employeeTypeBusiness.create(dto);
			
			daoFactory.commitTransaction();
			
		} catch (QuotesException exception){
			daoFactory.rollbackTransaction();
			throw exception;
			
		} catch (Exception exception){
			daoFactory.rollbackTransaction();
			String message = "There was an unexpected problem trying to create the new EmployeeType on 'create' method of EmployeeTypeFacadeImpl";
			throw QuotesException.buildTechnicalBussinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
		}
		
	}

	@Override
	public void update(EmployeeTypeDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			
			daoFactory.initTransaction();
			
			EmployeeTypeBusiness employeeTypeBusiness = new EmployeeTypeBusinessImpl(daoFactory);
			employeeTypeBusiness.update(dto);
			
			daoFactory.commitTransaction();
			
		} catch (QuotesException exception){
			daoFactory.rollbackTransaction();
			throw exception;
			
		} catch (Exception exception){
			daoFactory.rollbackTransaction();
			var message = "There was an unexpected problem trying to update the EmployeeType on 'update' method of EmployeeTypeFacadeImpl";
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
			
			EmployeeTypeBusiness employeeTypeBusiness = new EmployeeTypeBusinessImpl(daoFactory);
			employeeTypeBusiness.delete(id);
			
			daoFactory.commitTransaction();
			
		} catch (QuotesException exception){
			daoFactory.rollbackTransaction();
			throw exception;
			
		} catch (Exception exception){
			daoFactory.rollbackTransaction();
			var message = "There was an unexpected problem trying to delete the EmployeeType on 'delete' method of EmployeeTypeFacadeImpl";
			throw QuotesException.buildTechnicalBussinessLogicException(message);
		} finally {
			
			daoFactory.closeConnection();
			
		}
		
		
	}

	@Override
	public List<EmployeeTypeDTO> find(EmployeeTypeDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			
			daoFactory.initTransaction();
			
			EmployeeTypeBusiness employeeTypeBusiness = new EmployeeTypeBusinessImpl(daoFactory);
			return employeeTypeBusiness.find(dto);
			
		} catch (QuotesException exception){
			throw exception;
			
		} catch (Exception exception){
			var message = "There was an unexpected problem trying to find the new EmployeeType on 'find' method of EmployeeTypeFacadeImpl";
			throw QuotesException.buildTechnicalBussinessLogicException(message);
		} finally {
			
			daoFactory.closeConnection();
			
		}
	}
}
