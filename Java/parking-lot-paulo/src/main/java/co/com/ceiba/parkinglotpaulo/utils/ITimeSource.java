package co.com.ceiba.parkinglotpaulo.utils;

import java.text.ParseException;

public interface ITimeSource {
	
	/** Return the system time. 
	 * @throws ParseException */  
	  public long currentTimeMillis() throws ParseException;

}
