package co.edu.uco.quotes.api.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.quotes.api.controller.response.Response;
import co.edu.uco.quotes.api.controller.validators.Validator;
import co.edu.uco.quotes.api.controller.validators.employeestatus.CreateEmployeeStatusValidator;
import co.edu.uco.quotes.api.controller.validators.employeestatus.DeleteEmployeeStatusValidator;
import co.edu.uco.quotes.api.controller.validators.employeestatus.FindEmployeeStatusValidator;
import co.edu.uco.quotes.api.controller.validators.employeestatus.UpdateEmployeeStatusValidator;
import co.edu.uco.quotes.businesslogic.facade.EmployeeStatusFacade;
import co.edu.uco.quotes.businesslogic.facade.impl.EmployeeStatusFacadeImpl;
import co.edu.uco.quotes.crosscutting.exception.QuotesException;
import co.edu.uco.quotes.crosscutting.exception.enumeration.ExceptionType;
import co.edu.uco.quotes.dto.EmployeeStatusDTO;



@RestController
@RequestMapping("/api/v1/employeestatus")
public class EmployeeStatusController {
	
	@GetMapping("/dummys")
	public EmployeeStatusDTO getDummy() {
		System.out.println("Estoy en dummy!!");
		return new EmployeeStatusDTO();
	}

	@PostMapping
	public ResponseEntity<Response<EmployeeStatusDTO>> create(@RequestBody EmployeeStatusDTO dto) {
		Validator<EmployeeStatusDTO> validator = new CreateEmployeeStatusValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		Response<EmployeeStatusDTO> response = new Response<>();
		ResponseEntity<Response<EmployeeStatusDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		response.setData(new ArrayList<>());

		if (messages.isEmpty()) {
			try {
				EmployeeStatusFacade facade = new EmployeeStatusFacadeImpl();
				facade.create(dto);
				messages.add("EmployeeStatus was created successfully");
				statusCode = HttpStatus.OK;
			} catch (QuotesException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to register the new EmployeeStatus: Please, try again");
					System.err.println(exception.getLocation());
					System.err.println(exception.getType());
					System.err.println(exception.getTechnicalMessage());
					exception.getRootException().printStackTrace();
				} else {
					messages.add(exception.getUserMessage());
					System.err.println(exception.getLocation());
					System.err.println(exception.getType());
					System.err.println(exception.getUserMessage());
					exception.getRootException().printStackTrace();
				}
			} catch (Exception exception) {
				messages.add("There was an unexpected problem trying to create the new employee status type");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<EmployeeStatusDTO>>(response, statusCode);

		return responseEntity;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response<EmployeeStatusDTO>> update(@PathVariable("id") int id, @RequestBody EmployeeStatusDTO dto) {
		Validator<EmployeeStatusDTO> validator = new UpdateEmployeeStatusValidator();
		EmployeeStatusDTO updateDTO = new EmployeeStatusDTO(id, dto.getName());
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(updateDTO), new ArrayList<>());
		Response<EmployeeStatusDTO> response = new Response<>();
		ResponseEntity<Response<EmployeeStatusDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		response.setData(new ArrayList<>());

		if (messages.isEmpty()) {
			try {
				EmployeeStatusFacade facade = new EmployeeStatusFacadeImpl();
				facade.update(updateDTO);
				messages.add("EmployeeStatus was updated successfully");
				statusCode = HttpStatus.OK;
			} catch (QuotesException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to update the employee status type: Please, try again");
					System.err.println(exception.getLocation());
					System.err.println(exception.getType());
					System.err.println(exception.getTechnicalMessage());
					exception.getRootException().printStackTrace();
				} else {
					messages.add(exception.getUserMessage());
					System.err.println(exception.getLocation());
					System.err.println(exception.getType());
					System.err.println(exception.getUserMessage());
					exception.getRootException().printStackTrace();
				}
			} catch (Exception exception) {
				messages.add("There was an unexpected problem trying to update the employee status type");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<EmployeeStatusDTO>>(response, statusCode);

		return responseEntity;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response<EmployeeStatusDTO>> delete(@PathVariable("id") int id) {
		EmployeeStatusDTO dto = new EmployeeStatusDTO(id, "");
		Validator<EmployeeStatusDTO> validator = new DeleteEmployeeStatusValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		Response<EmployeeStatusDTO> response = new Response<>();
		ResponseEntity<Response<EmployeeStatusDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		response.setData(new ArrayList<>());
		
		if (messages.isEmpty()) {
			
			try {
				EmployeeStatusFacade facade = new EmployeeStatusFacadeImpl();
				facade.delete(dto.getId());
				messages.add("EmployeeStatus deleted successfully");
				statusCode = HttpStatus.OK;
			} catch (QuotesException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to delete the employee status type Please, try again");
					System.err.println(exception.getLocation());
					System.err.println(exception.getType());
					System.err.println(exception.getTechnicalMessage());
					exception.getRootException().printStackTrace();
				} else {
					messages.add(exception.getUserMessage());
					System.err.println(exception.getLocation());
					System.err.println(exception.getType());
					System.err.println(exception.getUserMessage());
					exception.getRootException().printStackTrace();
				}
			} catch (Exception exception) {
				messages.add("There was an unexpected problem trying to delete the employee status type");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<EmployeeStatusDTO>>(response, statusCode);

		return responseEntity;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<EmployeeStatusDTO>> findById(@PathVariable("id") int id) {
		EmployeeStatusDTO dto = new EmployeeStatusDTO(id, "");
		Validator<EmployeeStatusDTO> validator = new FindEmployeeStatusValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());;
		Response<EmployeeStatusDTO> response = new Response<>();
		ResponseEntity<Response<EmployeeStatusDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			
			try {
				EmployeeStatusFacade facade = new EmployeeStatusFacadeImpl();
				List<EmployeeStatusDTO> res = facade.find(dto);
				if(res.isEmpty()) {
					messages.add("employeeStatus not found");
				}else {
					messages.add("EmployeeStatuss were found successfully");
				}
				response.setData(res);
				statusCode = HttpStatus.OK;
			} catch (QuotesException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to find the employee status type Please, try again");
					System.err.println(exception.getLocation());
					System.err.println(exception.getType());
					System.err.println(exception.getTechnicalMessage());
					exception.getRootException().printStackTrace();
				} else {
					messages.add(exception.getUserMessage());
					System.err.println(exception.getLocation());
					System.err.println(exception.getType());
					System.err.println(exception.getUserMessage());
					exception.getRootException().printStackTrace();
				}
			} catch (Exception exception) {
				messages.add("There was an unexpected problem trying to find the employee status type");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<EmployeeStatusDTO>>(response, statusCode);

		return responseEntity;
	}

	@GetMapping
	public ResponseEntity<Response<EmployeeStatusDTO>> find() {
		List<String> messages = new ArrayList<>();
		Response<EmployeeStatusDTO> response = new Response<>();
		ResponseEntity<Response<EmployeeStatusDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;

		try {
			EmployeeStatusFacade facade = new EmployeeStatusFacadeImpl();
			response.setData(facade.find(new EmployeeStatusDTO()));
			messages.add("EmployeeStatuss were found successfully");
			statusCode = HttpStatus.OK;
		} catch (QuotesException exception) {
			if (ExceptionType.TECHNICAL.equals(exception.getType())) {
				messages.add("There was a problem trying to find the employee status types Please, try again");
				System.err.println(exception.getLocation());
				System.err.println(exception.getType());
				System.err.println(exception.getTechnicalMessage());
				exception.getRootException().printStackTrace();
			} else {
				messages.add(exception.getUserMessage());
				System.err.println(exception.getLocation());
				System.err.println(exception.getType());
				System.err.println(exception.getUserMessage());
				exception.getRootException().printStackTrace();
			}
		} catch (Exception exception) {
			messages.add("There was an unexpected problem trying to find the employee status types");
			exception.printStackTrace();
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<EmployeeStatusDTO>>(response, statusCode);

		return responseEntity;
	}


}
