package co.com.ceiba.parkinglotpaulo.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class Car extends Vehicle {
	
	public double getFee() {
		int elapsedHours = this.getElapsedHours();
		int elapsedDays = elapsedHours / 24;
		elapsedHours -= (elapsedDays * 24);
		if (elapsedHours >= 9) {
			elapsedDays++;
			elapsedHours = 0;
		}
		return this.getDailyRate() * elapsedDays + this.getHourlyRate() * elapsedHours;
	}
	
	@Override
	@Value("${car.hourlyRate}")
	protected void setHourlyRate(double hourlyRate) {
		super.setHourlyRate(hourlyRate);
	}
	
	@Override
	@Value("${car.dailyRate}")
	protected void setDailyRate(double dailyRate) {
		super.setDailyRate(dailyRate);
	}

}
