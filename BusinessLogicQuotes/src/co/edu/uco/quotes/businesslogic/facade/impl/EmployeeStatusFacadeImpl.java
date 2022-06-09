package co.edu.uco.quotes.businesslogic.facade.impl;

import java.util.List;

import co.edu.uco.businesslogic.business.impl.EmployeeStatusBusinessImpl;
import co.edu.uco.quotes.businesslogic.business.EmployeeStatusBusiness;
import co.edu.uco.quotes.businesslogic.facade.EmployeeStatusFacade;
import co.edu.uco.quotes.crosscutting.exception.QuotesException;
import co.edu.uco.quotes.data.factory.DAOFactory;
import co.edu.uco.quotes.dto.EmployeeStatusDTO;

public class EmployeeStatusFacadeImpl implements EmployeeStatusFacade {

	@Override
	public void create(EmployeeStatusDTO dto) {
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		
		try {
			
			daoFactory.initTransaction();
			
			EmployeeStatusBusiness employeeStatus = new EmployeeStatusBusinessImpl(daoFactory);
			employeeStatus.create(dto);
			
			daoFactory.commitTransaction();
			
		} catch (QuotesException exception){
			daoFactory.rollbackTransaction();
			throw exception;
			
		} catch (Exception exception){
			daoFactory.rollbackTransaction();
			String message = "There was an unexpected problem trying to create the new employeeStatus on 'create' method of employeestatusFacadeImpl";
			throw QuotesException.buildTechnicalBussinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
		}
	}

	@Override
	public void update(EmployeeStatusDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			
			daoFactory.initTransaction();
			EmployeeStatusBusiness employeeStatus = new EmployeeStatusBusinessImpl(daoFactory);
			employeeStatus.update(dto);
			
			daoFactory.commitTransaction();
			
		} catch (QuotesException exception){
			daoFactory.rollbackTransaction();
			throw exception;
			
		} catch (Exception exception){
			daoFactory.rollbackTransaction();
			var message = "There was an unexpected problem trying to update the employeeStatus on 'update' method of employeestatusFacadeImpl";
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
			EmployeeStatusBusiness employeeStatus = new EmployeeStatusBusinessImpl(daoFactory);
			employeeStatus.delete(id);
			
			daoFactory.commitTransaction();
			
		} catch (QuotesException exception){
			daoFactory.rollbackTransaction();
			throw exception;
			
		} catch (Exception exception){
			daoFactory.rollbackTransaction();
			var message = "There was an unexpected problem trying to delete the employeeStatus on 'update' method of employeestatusFacadeImpl";
			throw QuotesException.buildTechnicalBussinessLogicException(message);
		} finally {
			
			daoFactory.closeConnection();
			
		}
		
		
		
	}

	@Override
	public List<EmployeeStatusDTO> find(EmployeeStatusDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			
			daoFactory.initTransaction();
			
			EmployeeStatusBusiness employeeStatus = new EmployeeStatusBusinessImpl(daoFactory);
			return employeeStatus.find(dto);
			
		} catch (QuotesException exception){
			throw exception;
			
		} catch (Exception exception){
			var message = "There was an unexpected problem trying to find the new employeestatus on 'find' method of employeestatusFacadeImpl";
			throw QuotesException.buildTechnicalBussinessLogicException(message);
		} finally {
			
			daoFactory.closeConnection();
			
		}
	}

}