package co.com.ceiba.parkinglotpaulo.domain;

import javax.persistence.Entity;

@Entity
public final class Car extends Vehicle {

	@Override
	public void calculateFee(double dailyRate, double hourlyRate) {
		super.calculateFee(dailyRate, hourlyRate);
	}
}
