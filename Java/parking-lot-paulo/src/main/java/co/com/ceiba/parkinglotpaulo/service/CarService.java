package co.com.ceiba.parkinglotpaulo.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.com.ceiba.parkinglotpaulo.domain.Car;
import co.com.ceiba.parkinglotpaulo.repository.CarRepository;

@Service
public class CarService implements ICarService {
	
	//Constants
	@Value("${carService.message.maximumCarCountReached")
	private String maximumCarCountReachedMessage;
	@Value("${carService.carCapacity}")
	private int carCapacity;
	@Value("${car.hourlyRate}")
	private double hourlyRate;
	@Value("${car.dailyRate}")
	private double dailyRate;
	@Value("${carService.message.carIsAlreadyParked")
	private String carIsAlreadyParked;
	@Value("${carService.message.carCanParkOnlyInSundaysOrMondays")
	private String carCanParkOnlyInSundaysOrMondays;
	@Autowired
	private CarRepository carRepository;
	

	@Override
	public Car takeCarIn(String plate) throws Exception {
		boolean plateCanParkToday = checkIfPlateCanParkToday(plate);
		if(!plateCanParkToday) {
			throw new Exception(carCanParkOnlyInSundaysOrMondays);
		}
		Integer parkedCarsCount = carRepository.countParkedCars();
		if (parkedCarsCount >= carCapacity) {
			throw new Exception(maximumCarCountReachedMessage);
		}
		Car carCheck = carRepository.checkIfCarIsAlreadyParked(plate);
		if(carCheck != null) {
			throw new Exception(carIsAlreadyParked);
		}
		Car car = new Car();
		car.setPlate(plate);
		car.setEntranceTime(new Date());
		return carRepository.save(car);
	}

	private boolean checkIfPlateCanParkToday(String plate) {
		if (plate.toUpperCase().charAt(0) != 'A') {
			return true;
		}
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek != 1 && dayOfWeek != 2) {
        	return false;
        }
        return true;
	}

	@Override
	public Car getCarOut(String plate) {
		// TODO Auto-generated method stub
		return null;
	}

}
