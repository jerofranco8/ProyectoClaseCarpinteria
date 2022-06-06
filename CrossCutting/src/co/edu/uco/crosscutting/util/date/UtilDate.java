package co.edu.uco.crosscutting.util.date;

import java.util.Date;

import static co.edu.uco.crosscutting.util.object.UtilObject.getUtilObject;

public class UtilDate {
	
	private static final UtilDate INSTANCE = new UtilDate();
	
	public static UtilDate getUtilDate() {
		return INSTANCE;
	}
	
	public boolean isNull(Date date) {
		return getUtilObject().isNull(date);
	}
	
	public Date getDefault(Date value) {
		return getUtilObject().getDefault(value, new Date());
	}
	
	public boolean isBetween(Date date, Date init, Date end) {
		return (date.after(init) && date.before(end) ? true : false);
	}
	
	public boolean isBetweenIncludingInit(Date date, Date init, Date end) {
		return (isBetween(date,init,end) || date.equals(init) ? true : false);
	}
	
	public boolean isBetweenIncludingEnd(Date date, Date init, Date end) {
		return (isBetween(date,init,end) || date.equals(end) ? true : false);
	}
	
	public boolean isBetweenIncludingRanges(Date date, Date init, Date end) {
		return (isBetweenIncludingEnd(date,init,end) || isBetweenIncludingInit(date, init, end) ? true : false);
	}
	

	

}