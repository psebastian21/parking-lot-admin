package co.com.ceiba.parkinglotpaulo.utils;

import org.springframework.stereotype.Component;

@Component
public class SystemTimeSource implements ITimeSource {

	@Override
	public long currentTimeMillis() {
		return System.currentTimeMillis();
	}

}
