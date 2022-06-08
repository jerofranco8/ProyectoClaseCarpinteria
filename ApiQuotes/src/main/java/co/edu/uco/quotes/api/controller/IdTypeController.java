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
import co.edu.uco.quotes.api.controller.validators.idtype.CreateIdTypeValidator;
import co.edu.uco.quotes.api.controller.validators.idtype.DeleteIdTypeValidator;
import co.edu.uco.quotes.api.controller.validators.idtype.FindByIdIdTypeValidator;
import co.edu.uco.quotes.api.controller.validators.idtype.UpdateIdTypeValidator;
import co.edu.uco.quotes.businesslogic.facade.IdTypeFacade;
import co.edu.uco.quotes.businesslogic.facade.impl.IdTypeFacadeImpl;
import co.edu.uco.quotes.crosscutting.exception.QuotesException;
import co.edu.uco.quotes.crosscutting.exception.enumeration.ExceptionType;
import co.edu.uco.quotes.dto.IdTypeDTO;

@RestController
@RequestMapping("/api/v1/idtype")
public class IdTypeController {
	
	@GetMapping("/dummys")
	public IdTypeDTO getDummy() {
		System.out.println("Estoy en dummy!!");
		return new IdTypeDTO();
	}

	@PostMapping
	public ResponseEntity<Response<IdTypeDTO>> create(@RequestBody IdTypeDTO dto) {
		Validator<IdTypeDTO> validator = new CreateIdTypeValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		Response<IdTypeDTO> response = new Response<>();
		ResponseEntity<Response<IdTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		response.setData(new ArrayList<>());

		if (messages.isEmpty()) {
			try {
				IdTypeFacade facade = new IdTypeFacadeImpl();
				facade.create(dto);
				messages.add("IdType was created successfully");
				statusCode = HttpStatus.OK;
			} catch (QuotesException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to register the new id type: Please, try again");
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
				messages.add("There was an unexpected problem trying to create the new id type");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<IdTypeDTO>>(response, statusCode);

		return responseEntity;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response<IdTypeDTO>> update(@PathVariable("id") int id, @RequestBody IdTypeDTO dto) {
		Validator<IdTypeDTO> validator = new UpdateIdTypeValidator();
		IdTypeDTO updateDTO = new IdTypeDTO(id, dto.getName());
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(updateDTO), new ArrayList<>());
		Response<IdTypeDTO> response = new Response<>();
		ResponseEntity<Response<IdTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		response.setData(new ArrayList<>());

		if (messages.isEmpty()) {
			try {
				IdTypeFacade facade = new IdTypeFacadeImpl();
				facade.update(updateDTO);
				messages.add("IdType was updated successfully");
				statusCode = HttpStatus.OK;
			} catch (QuotesException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to update the id type: Please, try again");
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
				messages.add("There was an unexpected problem trying to update the id type");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<IdTypeDTO>>(response, statusCode);

		return responseEntity;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response<IdTypeDTO>> delete(@PathVariable("id") int id) {
		IdTypeDTO dto = new IdTypeDTO(id, "");
		Validator<IdTypeDTO> validator = new DeleteIdTypeValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());;
		Response<IdTypeDTO> response = new Response<>();
		ResponseEntity<Response<IdTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		response.setData(new ArrayList<>());
		
		if (messages.isEmpty()) {
			
			try {
				IdTypeFacade facade = new IdTypeFacadeImpl();
				facade.delete(dto.getId());
				messages.add("IdType deleted successfully");
				statusCode = HttpStatus.OK;
			} catch (QuotesException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to delete the id type Please, try again");
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
				messages.add("There was an unexpected problem trying to delete the id type");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<IdTypeDTO>>(response, statusCode);

		return responseEntity;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<IdTypeDTO>> findById(@PathVariable("id") int id) {
		IdTypeDTO dto = new IdTypeDTO(id, "");
		Validator<IdTypeDTO> validator = new FindByIdIdTypeValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());;
		Response<IdTypeDTO> response = new Response<>();
		ResponseEntity<Response<IdTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			
			try {
				IdTypeFacade facade = new IdTypeFacadeImpl();
				List<IdTypeDTO> res = facade.find(dto);
				if(res.isEmpty()) {
					messages.add("idType not found");
				}else {
					messages.add("IdTypes were found successfully");
				}
				response.setData(res);
				statusCode = HttpStatus.OK;
			} catch (QuotesException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to find the id type Please, try again");
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
				messages.add("There was an unexpected problem trying to find the id type");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<IdTypeDTO>>(response, statusCode);

		return responseEntity;
	}

	@GetMapping
	public ResponseEntity<Response<IdTypeDTO>> find() {
		List<String> messages = new ArrayList<>();
		Response<IdTypeDTO> response = new Response<>();
		ResponseEntity<Response<IdTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;

		try {
			IdTypeFacade facade = new IdTypeFacadeImpl();
			response.setData(facade.find(new IdTypeDTO()));
			messages.add("IdTypes were found successfully");
			statusCode = HttpStatus.OK;
		} catch (QuotesException exception) {
			if (ExceptionType.TECHNICAL.equals(exception.getType())) {
				messages.add("There was a problem trying to find the id types Please, try again");
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
			messages.add("There was an unexpected problem trying to find the id types");
			exception.printStackTrace();
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<IdTypeDTO>>(response, statusCode);

		return responseEntity;
	}

}
