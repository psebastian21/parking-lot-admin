package co.com.ceiba.parkinglotpaulo.domain.unit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


import co.com.ceiba.parkinglotpaulo.ParkingLotPauloApplication;
import co.com.ceiba.parkinglotpaulo.databuilder.CarDataBuilder;
import co.com.ceiba.parkinglotpaulo.domain.Car;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ParkingLotPauloApplication.class)
@TestPropertySource(locations="classpath:test.properties")
public class CarTests {
	
	@Value("${car.hourlyRate}")
	private double hourlyRate;
	@Value("${car.dailyRate}")
	private double dailyRate;
	@Value("${car.feeForZeroHours}")
	private Double feeForZeroHours;
	
	@Test
	public void theFeeForZeroHoursIsCorrect() {
		//Arrange
		Car car = new CarDataBuilder().build();
		//Act
		car.calculateFee(dailyRate, hourlyRate);
		Assert.assertEquals(car.getFee(), feeForZeroHours);
	}
	
}
