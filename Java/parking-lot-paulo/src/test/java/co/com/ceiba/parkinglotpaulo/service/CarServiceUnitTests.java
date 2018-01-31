package co.com.ceiba.parkinglotpaulo.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parkinglotpaulo.repository.CarRepository;
import co.com.ceiba.parkinglotpaulo.utils.ITimeSource;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:carServiceUnitTests.properties")
public class CarServiceUnitTests {
	
	//Constants
	@Value("${carService.message.maximumCarCountReached}")
	private String maximumCarCountReachedMessage;
	@Value("${carService.carCapacity}")
	private int carCapacity;
	@Value("${carService.hourlyRate}")
	private double hourlyRate;
	@Value("${carService.dailyRate}")
	private double dailyRate;
	@Value("${carService.message.carIsAlreadyParked}")
	private String carIsAlreadyParked;
	@Value("${carService.message.carCanParkOnlyInSundaysOrMondays}")
	private String carCanParkOnlyInSundaysOrMondays;
	@Value("${carService.message.carIsNotParked}")
	private String carIsNotParked;
	
	@TestConfiguration
    static class CarServiceUnitTestsContextConfiguration {
  
        @Bean
        public CarService sundayCarService(ITimeSource sundayTimeSource) {
        	CarService carService = new CarService();
        	carService.setTimeSource(sundayTimeSource);
            return carService;
        }
        
        @Bean
        public ITimeSource sundayTimeSource() {
        	return new ITimeSource() {

				@Override
				public long currentTimeMillis() {
					// TODO Auto-generated method stub
					return 0;
				}
        		
        	};
        }
        
    }
	
	@Autowired
    private CarService sundayCarService;
    @MockBean
    private CarRepository carRepository;

}
