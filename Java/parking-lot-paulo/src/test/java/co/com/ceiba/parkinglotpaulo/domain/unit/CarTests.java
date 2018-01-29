package co.com.ceiba.parkinglotpaulo.domain.unit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


import co.com.ceiba.parkinglotpaulo.ParkingLotPauloApplication;
import co.com.ceiba.parkinglotpaulo.domain.Car;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ParkingLotPauloApplication.class)
@TestPropertySource(locations="classpath:test.properties")
public class CarTests {
	
	private static final String defaultPlate = "TMX 464";
	@Autowired
	private Car car;

	@Test
	public void thePlateIsSetCorrectly() {
		//Arrange
		//Act
		car.setPlate(defaultPlate);
		String plate = car.getPlate();
		//Assert
		Assert.assertEquals(defaultPlate, plate);
	}
}
