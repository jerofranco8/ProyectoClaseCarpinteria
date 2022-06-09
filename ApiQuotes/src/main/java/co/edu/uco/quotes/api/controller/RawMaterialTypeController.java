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
import co.edu.uco.quotes.api.controller.validators.rawmaterialtype.CreatedRawMaterialTypeValidator;
import co.edu.uco.quotes.api.controller.validators.rawmaterialtype.DeleteRawMaterialTypeValidator;
import co.edu.uco.quotes.api.controller.validators.rawmaterialtype.FindRawMaterialTypeValidator;
import co.edu.uco.quotes.api.controller.validators.rawmaterialtype.UpdateRawMaterialTypeValidator;
import co.edu.uco.quotes.businesslogic.facade.RawMaterialTypeFacade;
import co.edu.uco.quotes.businesslogic.facade.impl.RawMaterialTypeFacadeImpl;
import co.edu.uco.quotes.crosscutting.exception.QuotesException;
import co.edu.uco.quotes.crosscutting.exception.enumeration.ExceptionType;
import co.edu.uco.quotes.dto.RawMaterialTypeDTO;



@RestController
@RequestMapping("/api/v1/rawmaterialtype")
public class RawMaterialTypeController {
	
	@GetMapping("/dummys")
	public RawMaterialTypeDTO getDummy() {
		System.out.println("Estoy en dummy!!");
		return new RawMaterialTypeDTO();
	}

	@PostMapping
	public ResponseEntity<Response<RawMaterialTypeDTO>> create(@RequestBody RawMaterialTypeDTO dto) {
		Validator<RawMaterialTypeDTO> validator = new CreatedRawMaterialTypeValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		Response<RawMaterialTypeDTO> response = new Response<>();
		ResponseEntity<Response<RawMaterialTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		response.setData(new ArrayList<>());

		if (messages.isEmpty()) {
			try {
				RawMaterialTypeFacade facade = new RawMaterialTypeFacadeImpl();
				facade.create(dto);
				messages.add("RawMaterialType was created successfully");
				statusCode = HttpStatus.OK;
			} catch (QuotesException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to register the new RawMaterialType: Please, try again");
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
				messages.add("There was an unexpected problem trying to create the new raw material type");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<RawMaterialTypeDTO>>(response, statusCode);

		return responseEntity;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response<RawMaterialTypeDTO>> update(@PathVariable("id") int id, @RequestBody RawMaterialTypeDTO dto) {
		Validator<RawMaterialTypeDTO> validator = new UpdateRawMaterialTypeValidator();
		RawMaterialTypeDTO updateDTO = new RawMaterialTypeDTO(id, dto.getName());
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(updateDTO), new ArrayList<>());
		Response<RawMaterialTypeDTO> response = new Response<>();
		ResponseEntity<Response<RawMaterialTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		response.setData(new ArrayList<>());

		if (messages.isEmpty()) {
			try {
				RawMaterialTypeFacade facade = new RawMaterialTypeFacadeImpl();
				facade.update(updateDTO);
				messages.add("RawMaterialType was updated successfully");
				statusCode = HttpStatus.OK;
			} catch (QuotesException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to update the raw material type: Please, try again");
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
				messages.add("There was an unexpected problem trying to update the raw material type");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<RawMaterialTypeDTO>>(response, statusCode);

		return responseEntity;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response<RawMaterialTypeDTO>> delete(@PathVariable("id") int id) {
		RawMaterialTypeDTO dto = new RawMaterialTypeDTO(id, "");
		Validator<RawMaterialTypeDTO> validator = new DeleteRawMaterialTypeValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());
		Response<RawMaterialTypeDTO> response = new Response<>();
		ResponseEntity<Response<RawMaterialTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		response.setData(new ArrayList<>());
		
		if (messages.isEmpty()) {
			
			try {
				RawMaterialTypeFacade facade = new RawMaterialTypeFacadeImpl();
				facade.delete(dto.getId());
				messages.add("RawMaterialType deleted successfully");
				statusCode = HttpStatus.OK;
			} catch (QuotesException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to delete the raw material type Please, try again");
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
				messages.add("There was an unexpected problem trying to delete the raw material type");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<RawMaterialTypeDTO>>(response, statusCode);

		return responseEntity;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<RawMaterialTypeDTO>> findById(@PathVariable("id") int id) {
		RawMaterialTypeDTO dto = new RawMaterialTypeDTO(id, "");
		Validator<RawMaterialTypeDTO> validator = new FindRawMaterialTypeValidator();
		List<String> messages = UtilObject.getUtilObject().getDefault(validator.validate(dto), new ArrayList<>());;
		Response<RawMaterialTypeDTO> response = new Response<>();
		ResponseEntity<Response<RawMaterialTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		
		if (messages.isEmpty()) {
			
			try {
				RawMaterialTypeFacade facade = new RawMaterialTypeFacadeImpl();
				List<RawMaterialTypeDTO> res = facade.find(dto);
				if(res.isEmpty()) {
					messages.add("rawMaterialType not found");
				}else {
					messages.add("RawMaterialTypes were found successfully");
				}
				response.setData(res);
				statusCode = HttpStatus.OK;
			} catch (QuotesException exception) {
				if (ExceptionType.TECHNICAL.equals(exception.getType())) {
					messages.add("There was a problem trying to find the raw material type Please, try again");
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
				messages.add("There was an unexpected problem trying to find the raw material type");
				exception.printStackTrace();
			}
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<RawMaterialTypeDTO>>(response, statusCode);

		return responseEntity;
	}

	@GetMapping
	public ResponseEntity<Response<RawMaterialTypeDTO>> find() {
		List<String> messages = new ArrayList<>();
		Response<RawMaterialTypeDTO> response = new Response<>();
		ResponseEntity<Response<RawMaterialTypeDTO>> responseEntity;
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;

		try {
			RawMaterialTypeFacade facade = new RawMaterialTypeFacadeImpl();
			response.setData(facade.find(new RawMaterialTypeDTO()));
			messages.add("RawMaterialTypes were found successfully");
			statusCode = HttpStatus.OK;
		} catch (QuotesException exception) {
			if (ExceptionType.TECHNICAL.equals(exception.getType())) {
				messages.add("There was a problem trying to find the raw material types Please, try again");
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
			messages.add("There was an unexpected problem trying to find the raw material types");
			exception.printStackTrace();
		}
		response.setMessages(messages);
		responseEntity = new ResponseEntity<Response<RawMaterialTypeDTO>>(response, statusCode);

		return responseEntity;
	}


}
