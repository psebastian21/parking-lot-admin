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
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.parkinglotpaulo.databuilder.MotorbikeDataBuilder;
import co.com.ceiba.parkinglotpaulo.domain.Motorbike;
import co.com.ceiba.parkinglotpaulo.repository.MotorbikeRepository;
import co.com.ceiba.parkinglotpaulo.utils.ITimeSource;
import co.com.ceiba.parkinglotpaulo.utils.ParkingException;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:motorbikeServiceUnitTests.properties")
public class MotorbikeServiceUnitTests {
	//Constants
	@Value("${motorbikeService.maximumMotorbikeCountReached}")
	private String maximumMotorbikeCountReachedMessage;
	@Value("${motorbikeService.motorbikeCapacity}")
	private int motorbikeCapacity;
	@Value("${motorbikeService.motorbikeIsAlreadyParked}")
	private String motorbikeIsAlreadyParked;
	@Value("${motorbikeService.motorbikeCanParkOnlyInSundaysOrMondays}")
	private String motorbikeCanParkOnlyInSundaysOrMondays;
	@Value("${motorbikeService.motorbikeIsNotParked}")
	private String motorbikeIsNotParked;
	private static String fakeDate = "2018/01/01";
		
	@TestConfiguration
	@Transactional
    protected static class MotorbikeServiceUnitTestsContextConfiguration {
  
        @Bean()
        public IMotorbikeService motorbikeService(ITimeSource timeSource) {
        	MotorbikeService motorbikeService = new MotorbikeService();
        	motorbikeService.setTimeSource(timeSource);
            return motorbikeService;
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
    private IMotorbikeService motorbikeService;
    @MockBean
    private MotorbikeRepository motorbikeRepositoryMock;
    
    @Before
    public void init() {
    	motorbikeService.setMotorbikeRepository(motorbikeRepositoryMock);
    }
    
    @Test
    public void plateBeginningInACantParkOutsideOfSundaysAndMondays() throws ParseException {
    	//Arrange
    	fakeDate = "2018/01/31";
    	Motorbike motorbike = new MotorbikeDataBuilder().withPlate(A_PLATE).build();
    	try {
    		//Act
    		motorbikeService.takeMotorbikeIn(motorbike);
    		Assert.fail();
    	}
    	catch(ParkingException e) {
    		Assert.assertEquals(motorbikeCanParkOnlyInSundaysOrMondays, e.getMessage());
    	}
    }
    
    @Test
    public void cantParkIfMotorbikePakingLotIsFull() throws ParseException {
    	//Arrange
    	Mockito.when(motorbikeRepositoryMock.countParkedMotorbikes()).thenReturn(motorbikeCapacity);
    	Motorbike motorbike = new MotorbikeDataBuilder().withPlate(NO_RESTRICTION_PLATE).build();
    	try {
    		//Act
    		motorbikeService.takeMotorbikeIn(motorbike);
    		Assert.fail();
    	}
    	catch(ParkingException e) {
    		Assert.assertEquals(maximumMotorbikeCountReachedMessage, e.getMessage());
    	}
    	
    }
    
    @Test
    public void cantParkAlreadyParkedMotorbike() throws ParseException{
    	//Arrange
    	Mockito.when(motorbikeRepositoryMock.countParkedMotorbikes()).thenReturn(0);
    	Mockito.when(motorbikeRepositoryMock.checkIfMotorbikeIsAlreadyParked(Mockito.anyString())).thenReturn(new MotorbikeDataBuilder().build());
    	Motorbike motorbike = new MotorbikeDataBuilder().withPlate(NO_RESTRICTION_PLATE).build();
    	try {
    		motorbikeService.takeMotorbikeIn(motorbike);
    		Assert.fail();
    	}
    	catch(ParkingException e) {
    		Assert.assertEquals(motorbikeIsAlreadyParked, e.getMessage());
    	}
    }
    
    @Test
    public void canParkNonRestrictedMotorbike() throws ParkingException, ParseException {
    	//Arrange
    	Mockito.when(motorbikeRepositoryMock.countParkedMotorbikes()).thenReturn(0);
    	Mockito.when(motorbikeRepositoryMock.checkIfMotorbikeIsAlreadyParked(Mockito.anyString())).thenReturn(null);
    	Mockito.when(motorbikeRepositoryMock.saveAndFlush(Mockito.any())).thenReturn(new MotorbikeDataBuilder().build());
    	Motorbike motorbikeToPark = new MotorbikeDataBuilder().withPlate(NO_RESTRICTION_PLATE).build();
    	//Act
    	Motorbike parkedMotorbike = motorbikeService.takeMotorbikeIn(motorbikeToPark);
    	//Assert
    	Assert.assertNotNull(parkedMotorbike);
    }
    
    @Test
    public void cantGetOutNonParkedMotorbike() throws ParseException {
    	//Arrange
    	Mockito.when(motorbikeRepositoryMock.checkIfMotorbikeIsAlreadyParked(Mockito.anyString())).thenReturn(null);
    	try {
    		//Act
    		motorbikeService.getMotorbikeOut(NO_RESTRICTION_PLATE);
    		Assert.fail();
    	}
    	catch(ParkingException e) {
    		//Assert
    		Assert.assertEquals(motorbikeIsNotParked, e.getMessage());
    	}
    }
    
    @Test
    public void canGetOutParkedMotorbike() throws ParkingException, ParseException {
    	//Arrange
    	Mockito.when(motorbikeRepositoryMock.checkIfMotorbikeIsAlreadyParked(Mockito.anyString())).thenReturn(new MotorbikeDataBuilder().build());
    	Mockito.when(motorbikeRepositoryMock.saveAndFlush(Mockito.any())).thenReturn(new MotorbikeDataBuilder().build());
    	//Act
    	Motorbike motorbike = motorbikeService.getMotorbikeOut(NO_RESTRICTION_PLATE);
    	//Assert
    	Assert.assertNotNull(motorbike);
    }
}