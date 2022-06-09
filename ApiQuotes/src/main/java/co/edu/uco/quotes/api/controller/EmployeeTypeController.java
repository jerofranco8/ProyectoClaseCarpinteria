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
import co.edu.uco.quotes.api.controller.validators.employeetype.CreateEmployeeTypeValidator;
import co.edu.uco.quotes.api.controller.validators.employeetype.DeleteEmployeeTypeValidator;
import co.edu.uco.quotes.api.controller.validators.employeetype.FindEmployeeTypeValidator;
import co.edu.uco.quotes.api.controller.validators.employeetype.UpdateEmployeeTypeValidator;
import co.edu.uco.quotes.businesslogic.facade.EmployeeTypeFacade;
import co.edu.uco.quotes.businesslogic.facade.impl.EmployeeTypeFacadeImpl;
import co.edu.uco.quotes.crosscutting.exception.QuotesException;
import co.edu.uco.quotes.crosscutting.exception.enumeration.ExceptionType;
import co.edu.uco.quotes.dto.EmployeeTypeDTO;

@RestController
@RequestMapping("/api/v1/employeetype")
public class EmployeeTypeController {
	
	@GetMapping("/dummys")
	public EmployeeTypeDTO getDummy() {
		System.out.println("Estoy en dummy!!");
		return new EmployeeTypeDTO();
	}

	@PostMapping
	public ResponseEntity<Response<EmployeeTypeDTO>> create(@RequestBody EmployeeTypeDTO dto) {
		Validator<EmployeeTypeDTO> validator = new CreateEmployeeTypeValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		Response<EmployeeTypeDTO> response = new Response<>();
		ResponseEntity<Response<EmployeeTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		response.setData(new ArrayList<>());

		if (messages.isEmpty()) {
			try {
				EmployeeTypeFacade facade = new EmployeeTypeFacadeImpl();
				facade.create(dto);
				messages.add("EmployeeType was created successfully");
				statusCode = HttpStatus.OK;
			} catch (QuotesException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to register the new employee type: Please, try again");
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
				messages.add("There was an unexpected problem trying to create the new employee type");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<EmployeeTypeDTO>>(response, statusCode);

		return responseEntity;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response<EmployeeTypeDTO>> update(@PathVariable("id") int id, @RequestBody EmployeeTypeDTO dto) {
		Validator<EmployeeTypeDTO> validator = new UpdateEmployeeTypeValidator();
		EmployeeTypeDTO updateDTO = new EmployeeTypeDTO(id, dto.getName(),dto.getSalary(),dto.getFunction());
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(updateDTO), new ArrayList<>());
		Response<EmployeeTypeDTO> response = new Response<>();
		ResponseEntity<Response<EmployeeTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		response.setData(new ArrayList<>());

		if (messages.isEmpty()) {
			try {
				EmployeeTypeFacade facade = new EmployeeTypeFacadeImpl();
				facade.update(updateDTO);
				messages.add("EmployeeType was updated successfully");
				statusCode = HttpStatus.OK;
			} catch (QuotesException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to update the employee type: Please, try again");
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
				messages.add("There was an unexpected problem trying to update the employee type");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<EmployeeTypeDTO>>(response, statusCode);

		return responseEntity;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response<EmployeeTypeDTO>> delete(@PathVariable("id") int id) {
		EmployeeTypeDTO dto = new EmployeeTypeDTO();
		dto.setId(id);
		Validator<EmployeeTypeDTO> validator = new DeleteEmployeeTypeValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());;
		Response<EmployeeTypeDTO> response = new Response<>();
		ResponseEntity<Response<EmployeeTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		response.setData(new ArrayList<>());
		
		if (messages.isEmpty()) {
			
			try {
				EmployeeTypeFacade facade = new EmployeeTypeFacadeImpl();
				facade.delete(dto.getId());
				messages.add("EmployeeType deleted successfully");
				statusCode = HttpStatus.OK;
			} catch (QuotesException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to delete the employee type Please, try again");
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
				messages.add("There was an unexpected problem trying to delete the employee type");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<EmployeeTypeDTO>>(response, statusCode);

		return responseEntity;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<EmployeeTypeDTO>> findById(@PathVariable("id") int id) {
		EmployeeTypeDTO dto = new EmployeeTypeDTO();
		dto.setId(id);
		Validator<EmployeeTypeDTO> validator = new FindEmployeeTypeValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());;
		Response<EmployeeTypeDTO> response = new Response<>();
		ResponseEntity<Response<EmployeeTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			
			try {
				EmployeeTypeFacade facade = new EmployeeTypeFacadeImpl();
				List<EmployeeTypeDTO> res = facade.find(dto);
				if(res.isEmpty()) {
					messages.add("employeeType not found");
				}else {
					messages.add("EmployeeTypes were found successfully");
				}
				response.setData(res);
				statusCode = HttpStatus.OK;
			} catch (QuotesException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to find the employee type Please, try again");
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
				messages.add("There was an unexpected problem trying to find the employee type");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<EmployeeTypeDTO>>(response, statusCode);

		return responseEntity;
	}

	@GetMapping
	public ResponseEntity<Response<EmployeeTypeDTO>> find() {
		List<String> messages = new ArrayList<>();
		Response<EmployeeTypeDTO> response = new Response<>();
		ResponseEntity<Response<EmployeeTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;

		try {
			EmployeeTypeFacade facade = new EmployeeTypeFacadeImpl();
			response.setData(facade.find(new EmployeeTypeDTO()));
			messages.add("EmployeeTypes were found successfully");
			statusCode = HttpStatus.OK;
		} catch (QuotesException exception) {
			if (ExceptionType.TECHNICAL.equals(exception.getType())) {
				messages.add("There was a problem trying to find the employee types Please, try again");
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
			messages.add("There was an unexpected problem trying to find the employee types");
			exception.printStackTrace();
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<EmployeeTypeDTO>>(response, statusCode);

		return responseEntity;
	}

}
