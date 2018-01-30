package co.com.ceiba.parkinglotpaulo.databuilder;

import java.util.Date;

import co.com.ceiba.parkinglotpaulo.domain.Car;

public class CarDataBuilder {
	
	private Car car;
	
	public CarDataBuilder() {
		car = new Car();
		Date now = new Date();
		car.setEntranceTime(now);
		car.setExitTime(now);
		car.setFee(0D);
		car.setId(0L);
		car.setPlate("default");
	}
	
	public Car build() {
		return car;
	}
	
	public CarDataBuilder withEntranceTime(Date entranceTime) {
		car.setEntranceTime(entranceTime);
		return this;
	}
	
	public CarDataBuilder withExitTime(Date exitTime) {
		car.setExitTime(exitTime);
		return this;
	}
	
	public CarDataBuilder withFee(Double fee) {
		car.setFee(fee);
		return this;
	}
	
	public CarDataBuilder withId(Long id) {
		car.setId(id);
		return this;
	}
	public CarDataBuilder withPlate(String plate) {
		car.setPlate(plate);
		return this;
	}

}
