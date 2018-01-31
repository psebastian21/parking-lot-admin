package co.com.ceiba.parkinglotpaulo.repository;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parkinglotpaulo.ParkingLotPauloApplication;
import co.com.ceiba.parkinglotpaulo.databuilder.CarDataBuilder;
import co.com.ceiba.parkinglotpaulo.domain.Car;
import org.junit.Assert;

@SpringBootTest(classes=ParkingLotPauloApplication.class)
@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:carRepositoryIntTests.properties")
public class CarRepositoryIntTests {
	
	private static final String NON_PARKED_PLATE = "TMF 211";
	private static final String PARKED_PLATE = "TKH 464";
	
	@Autowired
	private CarRepository carRepository;
	
	@After
	public void flushDBInstance() {
		carRepository.deleteAll();
		carRepository.flush();
	}
	
	@Test
	public void parkedCarCountIsCorrect() {
		//Arrange
		Car parkedCar1 = new CarDataBuilder().withExitTime(null).build();
		Car parkedCar2 = new CarDataBuilder().withExitTime(null).build();
		Car nonParkedCar1 = new CarDataBuilder().build();
		carRepository.saveAndFlush(parkedCar1);
		carRepository.saveAndFlush(parkedCar2);
		carRepository.saveAndFlush(nonParkedCar1);
		//Act
		int parkedCars = carRepository.countParkedCars();
		//Assert
		Assert.assertEquals(2, parkedCars);
	}
	
	@Test
	public void alreadyParkedCarIsDetected() {
		//Arrange
		Car parkedCar = new CarDataBuilder().withExitTime(null).build();
		carRepository.saveAndFlush(parkedCar);
		//Act
		Car foundCar = carRepository.checkIfCarIsAlreadyParked(parkedCar.getPlate());
		Assert.assertEquals(parkedCar.getPlate(),foundCar.getPlate());
	}
	
	@Test
	public void nonParkedCarIsntDetected() {
		//Arrange
		Car parkedCar = new CarDataBuilder().withExitTime(null).withPlate(PARKED_PLATE).build();
		carRepository.saveAndFlush(parkedCar);
		//Act
		Car carSearch = carRepository.checkIfCarIsAlreadyParked(NON_PARKED_PLATE);
		Assert.assertNull(carSearch);
	}

}
