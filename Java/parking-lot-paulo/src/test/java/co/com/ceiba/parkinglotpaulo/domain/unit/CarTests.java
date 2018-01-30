package co.com.ceiba.parkinglotpaulo.domain.unit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import co.com.ceiba.parkinglotpaulo.databuilder.CarDataBuilder;
import co.com.ceiba.parkinglotpaulo.domain.Car;

//@SpringBootTest(classes=ParkingLotPauloApplication.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@TestPropertySource(locations="classpath:carTests.properties")
public class CarTests {
	
	@Value("${carService.hourlyRate}")
	private static final double hourlyRate = 1000;
	@Value("${carService.dailyRate}")
	private static final double dailyRate = 8000;
	
	private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm";
	SimpleDateFormat dateFormat;
	
	private static final String ENTRANCE_TIME_1 = "2017/12/01 00:00";
	
	private static final String EXIT_TIME_1 = "2017/12/01 08:00";
	private static final String EXIT_TIME_2 = "2017/12/01 08:01";
	private static final String EXIT_TIME_3 = "2017/12/01 22:45";
	private static final String EXIT_TIME_4 = "2017/12/02 00:00";
	private static final String EXIT_TIME_5 = "2017/12/02 00:50";
	private static final String EXIT_TIME_6 = "2017/12/02 07:22";
	private static final String EXIT_TIME_7 = "2017/12/02 08:16";
	private static final String EXIT_TIME_8 = "2017/12/04 00:00";
	private static final String EXIT_TIME_9 = "2018/01/01 00:00";
	
	@Before
	public void init() {
		dateFormat = new SimpleDateFormat(DATE_FORMAT);
	}
	
	@Test
	public void theFeeForZeroHoursIsCorrect() {
		//Arrange
		Car car = new CarDataBuilder().build();
		//Act
		car.calculateFee(dailyRate, hourlyRate);
		Assert.assertEquals(0d, car.getFee(), 0d);
	}
	
	@Test
	public void theFeeForEightHoursIsCorrect() throws ParseException {
		//Arrange
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_1);
		Car car = new CarDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).build();
		//Act
		car.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(hourlyRate * 8, car.getFee(), 0d);
	}
	
	@Test
	public void theFeeForNineHoursIsCorrect() throws ParseException {
		//Arrange
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_2);
		Car car = new CarDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).build();
		//Act
		car.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 1, car.getFee(), 0d);
	}
	
	@Test
	public void theFeeForTuentyThreeHoursIsCorrect() throws ParseException {
		//Arrange
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_3);
		Car car = new CarDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).build();
		//Act
		car.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 1, car.getFee(), 0d);
	}
	@Test
	public void theFeeForTuentyFourHoursIsCorrect() throws ParseException {
		//Arrange
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_4);
		Car car = new CarDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).build();
		//Act
		car.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 1, car.getFee(), 0d);
	}
	@Test
	public void theFeeForTuentyFiveHoursIsCorrect() throws ParseException {
		//Arrange
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_5);
		Car car = new CarDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).build();
		//Act
		car.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 1 + hourlyRate * 1, car.getFee(), 0d);
	}
	@Test
	public void theFeeForThirtyTwoHoursIsCorrect() throws ParseException {
		//Arrange
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_6);
		Car car = new CarDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).build();
		//Act
		car.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 1 + hourlyRate * 8, car.getFee(), 0d);
	}
	@Test
	public void theFeeForThirtyThreeHoursIsCorrect() throws ParseException {
		//Arrange
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_7);
		Car car = new CarDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).build();
		//Act
		car.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 2, car.getFee(), 0d);
	}
	@Test
	public void theFeeForThreeDaysIsCorrect() throws ParseException {
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_8);
		Car car = new CarDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).build();
		//Act
		car.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 3, car.getFee(), 0d);
	}
	@Test
	public void theFeeForThirtyOneDaysIsCorrect() throws ParseException {
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_9);
		Car car = new CarDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).build();
		//Act
		car.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 31, car.getFee(), 0d);
	}
	
	
}
