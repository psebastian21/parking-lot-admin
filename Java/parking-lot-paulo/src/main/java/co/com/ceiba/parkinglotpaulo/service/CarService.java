package co.com.ceiba.parkinglotpaulo.service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.com.ceiba.parkinglotpaulo.domain.Car;
import co.com.ceiba.parkinglotpaulo.repository.CarRepository;
import co.com.ceiba.parkinglotpaulo.utils.ITimeSource;
import co.com.ceiba.parkinglotpaulo.utils.ParkingException;

@Service
public class CarService implements ICarService {
	
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
	
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private ITimeSource timeSource;
	

	@Override
	public Car takeCarIn(String plate) throws ParkingException, ParseException {
		boolean plateCanParkToday = checkIfPlateCanParkToday(plate);
		if(!plateCanParkToday) {
			throw new ParkingException(carCanParkOnlyInSundaysOrMondays);
		}
		Integer parkedCarsCount = carRepository.countParkedCars();
		if (parkedCarsCount >= carCapacity) {
			throw new ParkingException(maximumCarCountReachedMessage);
		}
		Car carCheck = carRepository.checkIfCarIsAlreadyParked(plate);
		if(carCheck != null) {
			throw new ParkingException(carIsAlreadyParked);
		}
		Car car = new Car();
		car.setPlate(plate);
		car.setEntranceTime(new Date(timeSource.currentTimeMillis()));
		return carRepository.saveAndFlush(car);
	}

	private boolean checkIfPlateCanParkToday(String plate) throws ParseException {
		if (plate.toUpperCase().charAt(0) != 'A') {
			return true;
		}
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(timeSource.currentTimeMillis()));
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == 1 || dayOfWeek == 2;
	}

	@Override
	public Car getCarOut(String plate) throws ParkingException, ParseException {
		Car c = carRepository.checkIfCarIsAlreadyParked(plate);
		if (c == null) {
			throw new ParkingException(carIsNotParked);
		}
		c.setExitTime(new Date(timeSource.currentTimeMillis()));
		c.calculateFee(dailyRate, hourlyRate);
		return carRepository.saveAndFlush(c);
	}

	public CarRepository getCarRepository() {
		return carRepository;
	}

	public void setCarRepository(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	@Override
	public ITimeSource getTimeSource() {
		return timeSource;
	}
	@Override
	public void setTimeSource(ITimeSource timeSource) {
		this.timeSource = timeSource;
	}

}
