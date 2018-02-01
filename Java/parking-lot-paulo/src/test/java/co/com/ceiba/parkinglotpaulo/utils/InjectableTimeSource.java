package co.com.ceiba.parkinglotpaulo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InjectableTimeSource implements ITimeSource{

	private String injectedTime;
	
	public InjectableTimeSource() {
		injectedTime = "2018/01/01";
	}

	@Override
	public long currentTimeMillis() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date sundayDate = dateFormat.parse(injectedTime);
		return sundayDate.getTime();
	}
	public void setInjectedTime(String injectedTime) {
		this.injectedTime = injectedTime;
	}
	
}