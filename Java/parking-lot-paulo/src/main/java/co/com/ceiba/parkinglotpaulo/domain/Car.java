package co.com.ceiba.parkinglotpaulo.domain;

import org.springframework.stereotype.Component;

@Component
public final class Car extends Vehicle {
	
	public void calculateFee(double dailyRate, double hourlyRate) {
		int elapsedHours = this.getElapsedHours();
		int elapsedDays = elapsedHours / 24;
		elapsedHours -= (elapsedDays * 24);
		if (elapsedHours >= 9) {
			elapsedDays++;
			elapsedHours = 0;
		}
		setFee(dailyRate * elapsedDays + hourlyRate * elapsedHours);
	}

}
