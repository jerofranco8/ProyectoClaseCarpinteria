package co.edu.uco.quotes.crosscutting.exception;

import static co.edu.uco.crosscutting.util.object.UtilObject.getUtilObject;

import co.edu.uco.crosscutting.exception.GeneralException;
import co.edu.uco.quotes.crosscutting.exception.enumeration.ExceptionLocation;
import co.edu.uco.quotes.crosscutting.exception.enumeration.ExceptionType;

public class QuotesException extends GeneralException {

	private QuotesException(String userMessage, String technicalMessage, Exception rootException, ExceptionType type,
			ExceptionLocation location) {
		super(userMessage, technicalMessage, rootException);
		setType(type);
		setLocation(location);
	}

	public static QuotesException buildUserException(String userMessage) {
		return new QuotesException(userMessage, userMessage, null, ExceptionType.BUSINESS, null);
	}

	public static QuotesException buildTechnicalException(String technicalMessage) {
		return new QuotesException(null, technicalMessage, null, ExceptionType.TECHNICAL, null);
	}

	public static QuotesException buildTechnicalException(String technicalMessage, Exception rootException, ExceptionLocation location) {
		return new QuotesException(null, technicalMessage, rootException, ExceptionType.TECHNICAL, location);
	}
	
	public static QuotesException buildTechnicalDataException(String technicalMessage) {
		return new QuotesException(null, technicalMessage, null, ExceptionType.TECHNICAL, ExceptionLocation.DATA);
	}
	
	public static QuotesException buildTechnicalBussinessLogicException(String technicalMessage) {
		return new QuotesException(null, technicalMessage, null, ExceptionType.TECHNICAL, ExceptionLocation.BUSINESS_LOGIC);
	}
	
	public static QuotesException buildBussinessLogicException(String businessMessage) {
		return new QuotesException(businessMessage, null, null, ExceptionType.BUSINESS, ExceptionLocation.BUSINESS_LOGIC);
	}
	
	public static QuotesException buildTechnicalDataException(String technicalMessage, Exception rootException) {
		return new QuotesException(null, technicalMessage, rootException, ExceptionType.TECHNICAL, ExceptionLocation.DATA);
	}

	public static QuotesException build(String userMessage, String technicalMessage) {
		return new QuotesException(userMessage, technicalMessage, null, null, null);
	}

	public static QuotesException build(String userMessage, String technicalMessage, Exception rootException) {
		return new QuotesException(userMessage, technicalMessage, rootException, null, null);
	}

	private static final long serialVersionUID = 625249639280789375L;

	private ExceptionType type;
	private ExceptionLocation location;

	private void setType(ExceptionType type) {
		this.type = getUtilObject().getDefault(type, ExceptionType.GENERAL);
	}

	private void setLocation(ExceptionLocation location) {
		this.location = location;
	}
	
	public ExceptionLocation getLocation() {
		return location;
	}

	public ExceptionType getType() {
		return type;
	}

}
