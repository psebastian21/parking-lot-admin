package co.com.ceiba.parkinglotpaulo.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import co.com.ceiba.parkinglotpaulo.databuilder.MotorbikeDataBuilder;

public class MotorbikeUnitTests {
	private static final double hourlyRate = 500;
	private static final double dailyRate = 4000;
	private static final double bigCylinderSurcharge = 2000;
	
	private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm";
	SimpleDateFormat dateFormat;
	
	private static final int BIG_CYLINDER = 600;
	
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
		Motorbike car = new MotorbikeDataBuilder().build();
		//Act
		car.calculateFee(dailyRate, hourlyRate);
		Assert.assertEquals(0d, car.getFee(), 0d);
	}
	@Test
	public void theFeeForZeroHoursAndBigCylinderIsCorrect() {
		//Arrange
		Motorbike car = new MotorbikeDataBuilder().withCylinderCapacity(BIG_CYLINDER).build();
		//Act
		car.calculateFee(dailyRate, hourlyRate);
		Assert.assertEquals(bigCylinderSurcharge, car.getFee(), 0d);
	}
	
	@Test
	public void theFeeForEightHoursIsCorrect() throws ParseException {
		//Arrange
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_1);
		Motorbike motorbike = new MotorbikeDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).build();
		//Act
		motorbike.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(hourlyRate * 8, motorbike.getFee(), 0d);
	}
	@Test
	public void theFeeForEightHoursAndBigCylinderIsCorrect() throws ParseException {
		//Arrange
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_1);
		Motorbike motorbike = new MotorbikeDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).withCylinderCapacity(BIG_CYLINDER).build();
		//Act
		motorbike.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(hourlyRate * 8 + bigCylinderSurcharge, motorbike.getFee(), 0d);
	}
	@Test
	public void theFeeForNineHoursIsCorrect() throws ParseException {
		//Arrange
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_2);
		Motorbike motorbike = new MotorbikeDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).build();
		//Act
		motorbike.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 1, motorbike.getFee(), 0d);
	}
	@Test
	public void theFeeForNineHoursAndBigCylinderIsCorrect() throws ParseException {
		//Arrange
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_2);
		Motorbike motorbike = new MotorbikeDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).withCylinderCapacity(BIG_CYLINDER).build();
		//Act
		motorbike.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 1 + bigCylinderSurcharge, motorbike.getFee(), 0d);
	}
	@Test
	public void theFeeForTuentyThreeHoursIsCorrect() throws ParseException {
		//Arrange
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_3);
		Motorbike motorbike = new MotorbikeDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).build();
		//Act
		motorbike.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 1, motorbike.getFee(), 0d);
	}
	@Test
	public void theFeeForTuentyThreeHoursAndBigCylinderIsCorrect() throws ParseException {
		//Arrange
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_3);
		Motorbike motorbike = new MotorbikeDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).withCylinderCapacity(BIG_CYLINDER).build();
		//Act
		motorbike.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 1 + bigCylinderSurcharge, motorbike.getFee(), 0d);
	}
	@Test
	public void theFeeForTuentyFourHoursIsCorrect() throws ParseException {
		//Arrange
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_4);
		Motorbike motorbike = new MotorbikeDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).build();
		//Act
		motorbike.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 1, motorbike.getFee(), 0d);
	}
	@Test
	public void theFeeForTuentyFourHoursAndBigCylinderIsCorrect() throws ParseException {
		//Arrange
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_4);
		Motorbike motorbike = new MotorbikeDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).withCylinderCapacity(BIG_CYLINDER).build();
		//Act
		motorbike.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 1 + bigCylinderSurcharge, motorbike.getFee(), 0d);
	}
	@Test
	public void theFeeForTuentyFiveHoursIsCorrect() throws ParseException {
		//Arrange
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_5);
		Motorbike motorbike = new MotorbikeDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).build();
		//Act
		motorbike.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 1 + hourlyRate * 1, motorbike.getFee(), 0d);
	}
	@Test
	public void theFeeForTuentyFiveHoursAndBigCylinderIsCorrect() throws ParseException {
		//Arrange
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_5);
		Motorbike motorbike = new MotorbikeDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).withCylinderCapacity(BIG_CYLINDER).build();
		//Act
		motorbike.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 1 + hourlyRate * 1 + bigCylinderSurcharge, motorbike.getFee(), 0d);
	}
	@Test
	public void theFeeForThirtyTwoHoursIsCorrect() throws ParseException {
		//Arrange
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_6);
		Motorbike motorbike = new MotorbikeDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).build();
		//Act
		motorbike.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 1 + hourlyRate * 8, motorbike.getFee(), 0d);
	}
	@Test
	public void theFeeForThirtyTwoHoursAndBigCylinderIsCorrect() throws ParseException {
		//Arrange
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_6);
		Motorbike motorbike = new MotorbikeDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).withCylinderCapacity(BIG_CYLINDER).build();
		//Act
		motorbike.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 1 + hourlyRate * 8 + bigCylinderSurcharge, motorbike.getFee(), 0d);
	}
	@Test
	public void theFeeForThirtyThreeHoursIsCorrect() throws ParseException {
		//Arrange
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_7);
		Motorbike motorbike = new MotorbikeDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).build();
		//Act
		motorbike.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 2, motorbike.getFee(), 0d);
	}
	@Test
	public void theFeeForThirtyThreeHoursAndBigCylinderIsCorrect() throws ParseException {
		//Arrange
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_7);
		Motorbike motorbike = new MotorbikeDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).withCylinderCapacity(BIG_CYLINDER).build();
		//Act
		motorbike.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 2 + bigCylinderSurcharge, motorbike.getFee(), 0d);
	}
	@Test
	public void theFeeForThreeDaysIsCorrect() throws ParseException {
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_8);
		Motorbike motorbike = new MotorbikeDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).build();
		//Act
		motorbike.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 3, motorbike.getFee(), 0d);
	}
	@Test
	public void theFeeForThreeDaysAndBigCylinderIsCorrect() throws ParseException {
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_8);
		Motorbike motorbike = new MotorbikeDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).withCylinderCapacity(BIG_CYLINDER).build();
		//Act
		motorbike.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 3 + bigCylinderSurcharge, motorbike.getFee(), 0d);
	}
	@Test
	public void theFeeForThirtyOneDaysIsCorrect() throws ParseException {
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_9);
		Motorbike motorbike = new MotorbikeDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).build();
		//Act
		motorbike.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 31, motorbike.getFee(), 0d);
	}
	@Test
	public void theFeeForThirtyOneDaysAndBigCylinderIsCorrect() throws ParseException {
		Date entranceTime = dateFormat.parse(ENTRANCE_TIME_1);
		Date exitTime = dateFormat.parse(EXIT_TIME_9);
		Motorbike motorbike = new MotorbikeDataBuilder().withEntranceTime(entranceTime).withExitTime(exitTime).withCylinderCapacity(BIG_CYLINDER).build();
		//Act
		motorbike.calculateFee(dailyRate, hourlyRate);
		//Assert
		Assert.assertEquals(dailyRate * 31 + bigCylinderSurcharge, motorbike.getFee(), 0d);
	}
}
