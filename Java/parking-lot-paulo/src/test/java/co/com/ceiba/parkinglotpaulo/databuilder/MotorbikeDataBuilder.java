package co.com.ceiba.parkinglotpaulo.databuilder;

import java.util.Date;

import co.com.ceiba.parkinglotpaulo.domain.Motorbike;

public class MotorbikeDataBuilder {
	private Motorbike motorbike;
	
	public MotorbikeDataBuilder() {
		motorbike = new Motorbike();
		Date now = new Date();
		motorbike.setEntranceTime(now);
		motorbike.setExitTime(now);
		motorbike.setFee(0D);
		motorbike.setId(0L);
		motorbike.setPlate("default");
		motorbike.setCylinderCapacity(125);
	}
	
	public Motorbike build() {
		return motorbike;
	}
	
	public MotorbikeDataBuilder withEntranceTime(Date entranceTime) {
		motorbike.setEntranceTime(entranceTime);
		return this;
	}
	
	public MotorbikeDataBuilder withExitTime(Date exitTime) {
		motorbike.setExitTime(exitTime);
		return this;
	}
	
	public MotorbikeDataBuilder withFee(Double fee) {
		motorbike.setFee(fee);
		return this;
	}
	
	public MotorbikeDataBuilder withId(Long id) {
		motorbike.setId(id);
		return this;
	}
	public MotorbikeDataBuilder withPlate(String plate) {
		motorbike.setPlate(plate);
		return this;
	}
	public MotorbikeDataBuilder withCylinderCapacity(int cylinderCapacity) {
		motorbike.setCylinderCapacity(cylinderCapacity);
		return this;
	}
}
