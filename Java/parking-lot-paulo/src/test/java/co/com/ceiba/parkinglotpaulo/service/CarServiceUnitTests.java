package co.com.ceiba.parkinglotpaulo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parkinglotpaulo.databuilder.CarDataBuilder;
import co.com.ceiba.parkinglotpaulo.domain.Car;
import co.com.ceiba.parkinglotpaulo.repository.CarRepository;
import co.com.ceiba.parkinglotpaulo.utils.ITimeSource;
import co.com.ceiba.parkinglotpaulo.utils.ParkingException;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:carServiceUnitTests.properties")
public class CarServiceUnitTests {
	
	//Constants
	@Value("${carService.message.maximumCarCountReached}")
	private String maximumCarCountReachedMessage;
	@Value("${carService.carCapacity}")
	private int carCapacity;
	@Value("${carService.message.carIsAlreadyParked}")
	private String carIsAlreadyParked;
	@Value("${carService.message.carCanParkOnlyInSundaysOrMondays}")
	private String carCanParkOnlyInSundaysOrMondays;
	@Value("${carService.message.carIsNotParked}")
	private String carIsNotParked;
	private static String fakeDate = "2018/01/01";
	
	@TestConfiguration
    protected static class CarServiceUnitTestsContextConfiguration {
  
        @Bean()
        public ICarService carService(ITimeSource timeSource) {
        	CarService carService = new CarService();
        	carService.setTimeSource(timeSource);
            return carService;
        }
        
        @Bean()
        public ITimeSource timeSource() {
        	return new ITimeSource() {

				@Override
				public long currentTimeMillis() throws ParseException {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					Date sundayDate = dateFormat.parse(fakeDate);
					return sundayDate.getTime();
				}
        		
        	};
        }
        
    }
	
	private static final String A_PLATE = 	"aaa 123";
	private static final String NO_RESTRICTION_PLATE = 	"zaa 123";
	
	@Autowired
    private ICarService carService;
    @MockBean
    private CarRepository carRepositoryMock;
    
    @Before
    public void init() {
    	carService.setCarRepository(carRepositoryMock);
    }
    
    @Test
    public void plateBeginningInACantParkOutsideOfSundaysAndMondays() throws ParseException {
    	//Arrange
    	fakeDate = "2018/01/31";
    	try {
    		//Act
    		carService.takeCarIn(A_PLATE);
    		Assert.fail();
    	}
    	catch(ParkingException e) {
    		Assert.assertEquals(carCanParkOnlyInSundaysOrMondays, e.getMessage());
    	}
    }
    
    @Test
    public void cantParkIfCarPakingLotIsFull() throws ParseException {
    	//Arrange
    	Mockito.when(carRepositoryMock.countParkedCars()).thenReturn(carCapacity);
    	try {
    		//Act
    		carService.takeCarIn(NO_RESTRICTION_PLATE);
    		Assert.fail();
    	}
    	catch(ParkingException e) {
    		Assert.assertEquals(maximumCarCountReachedMessage, e.getMessage());
    	}
    	
    }
    
    @Test
    public void cantParkAlreadyParkedCar() throws ParseException{
    	//Arrange
    	Mockito.when(carRepositoryMock.countParkedCars()).thenReturn(0);
    	Mockito.when(carRepositoryMock.checkIfCarIsAlreadyParked(Mockito.anyString())).thenReturn(new CarDataBuilder().build());
    	try {
    		carService.takeCarIn(NO_RESTRICTION_PLATE);
    		Assert.fail();
    	}
    	catch(ParkingException e) {
    		Assert.assertEquals(carIsAlreadyParked, e.getMessage());
    	}
    }
    
    @Test
    public void canParkNonRestrictedCar() throws ParkingException, ParseException {
    	//Arrange
    	Mockito.when(carRepositoryMock.countParkedCars()).thenReturn(0);
    	Mockito.when(carRepositoryMock.checkIfCarIsAlreadyParked(Mockito.anyString())).thenReturn(null);
    	Mockito.when(carRepositoryMock.saveAndFlush(Mockito.any())).thenReturn(new CarDataBuilder().build());
    	//Act
    	Car car = carService.takeCarIn(NO_RESTRICTION_PLATE);
    	//Assert
    	Assert.assertNotNull(car);
    }
    
    @Test
    public void cantGetOutNonParkedCar() throws ParseException {
    	//Arrange
    	Mockito.when(carRepositoryMock.checkIfCarIsAlreadyParked(Mockito.anyString())).thenReturn(null);
    	try {
    		//Act
    		carService.getCarOut(NO_RESTRICTION_PLATE);
    		Assert.fail();
    	}
    	catch(ParkingException e) {
    		//Assert
    		Assert.assertEquals(carIsNotParked, e.getMessage());
    	}
    }
    
    @Test
    public void canGetOutParkedCar() throws ParkingException, ParseException {
    	//Arrange
    	Mockito.when(carRepositoryMock.checkIfCarIsAlreadyParked(Mockito.anyString())).thenReturn(new CarDataBuilder().build());
    	Mockito.when(carRepositoryMock.saveAndFlush(Mockito.any())).thenReturn(new CarDataBuilder().build());
    	//Act
    	Car car = carService.getCarOut(NO_RESTRICTION_PLATE);
    	//Assert
    	Assert.assertNotNull(car);
    }
    

}
