package co.com.ceiba.parkinglotpaulo.repository;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parkinglotpaulo.ParkingLotPauloApplication;
import co.com.ceiba.parkinglotpaulo.databuilder.MotorbikeDataBuilder;
import co.com.ceiba.parkinglotpaulo.domain.Motorbike;

@SpringBootTest(classes=ParkingLotPauloApplication.class)
@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:motorbikeRepositoryIntTests.properties")
public class MotorbikeRepositoryIntTests {
	private static final String NON_PARKED_PLATE = "TMF 211";
	private static final String PARKED_PLATE = "TKH 464";
	
	@Autowired
	private MotorbikeRepository motorbikeRepository;
	
	@After
	public void flushDBInstance() {
		motorbikeRepository.deleteAll();
		motorbikeRepository.flush();
	}
	
	@Test
	public void parkedMotorbikeCountIsCorrect() {
		//Arrange
		Motorbike parkedMotorbike1 = new MotorbikeDataBuilder().withExitTime(null).build();
		Motorbike parkedMotorbike2 = new MotorbikeDataBuilder().withExitTime(null).build();
		Motorbike nonParkedMotorbike1 = new MotorbikeDataBuilder().build();
		motorbikeRepository.saveAndFlush(parkedMotorbike1);
		motorbikeRepository.saveAndFlush(parkedMotorbike2);
		motorbikeRepository.saveAndFlush(nonParkedMotorbike1);
		//Act
		int parkedmotorbikes = motorbikeRepository.countParkedMotorbikes();
		//Assert
		Assert.assertEquals(2, parkedmotorbikes);
	}
	
	@Test
	public void alreadyParkedMotorbikeIsDetected() {
		//Arrange
		Motorbike parkedMotorbike = new MotorbikeDataBuilder().withExitTime(null).build();
		motorbikeRepository.saveAndFlush(parkedMotorbike);
		//Act
		Motorbike foundMotorbike = motorbikeRepository.checkIfMotorbikeIsAlreadyParked(parkedMotorbike.getPlate());
		//Assert
		Assert.assertEquals(parkedMotorbike.getPlate(),foundMotorbike.getPlate());
	}
	
	@Test
	public void nonParkedMotorbikeIsntDetected() {
		//Arrange
		Motorbike parkedMotorbike = new MotorbikeDataBuilder().withExitTime(null).withPlate(PARKED_PLATE).build();
		motorbikeRepository.saveAndFlush(parkedMotorbike);
		//Act
		Motorbike motorbikeSearch = motorbikeRepository.checkIfMotorbikeIsAlreadyParked(NON_PARKED_PLATE);
		//Assert
		Assert.assertNull(motorbikeSearch);
	}
	@Test
	public void listOfParkedMotorbikesIsCorrect() {
		//Arrange
		Motorbike parkedMotorbike1 = new MotorbikeDataBuilder().withExitTime(null).build();
		Motorbike parkedMotorbike2 = new MotorbikeDataBuilder().withExitTime(null).build();
		Motorbike nonParkedMotorbike1 = new MotorbikeDataBuilder().build();
		motorbikeRepository.saveAndFlush(parkedMotorbike1);
		motorbikeRepository.saveAndFlush(parkedMotorbike2);
		motorbikeRepository.saveAndFlush(nonParkedMotorbike1);
		//Act
		List<Motorbike> parkedMotorbikes = motorbikeRepository.findAllParkedMotorbikes();
		//Assert
		Assert.assertEquals(2, parkedMotorbikes.size());
	}
}
