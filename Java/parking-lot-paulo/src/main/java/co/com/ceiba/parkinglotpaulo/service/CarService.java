package co.com.ceiba.parkinglotpaulo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import co.com.ceiba.parkinglotpaulo.domain.Car;
import co.com.ceiba.parkinglotpaulo.repository.CarRepository;

public class CarService implements ICarService {
	
	@Value("${car.hourlyRate}")
	private double hourlyRate;
	@Value("${car.dailyRate}")
	private double dailyRate;
	
	@Autowired
	private CarRepository carRepository;

	@Override
	public Car takeCarIn(String plate) throws Exception {
		Integer parkedCarsCount = carRepository.countParkedCars();
		if (parkedCarsCount >= 20) {
			throw new Exception();
		}
		Car car = new Car();
		car.setEntranceTime(new Date());
		return carRepository.save(car);
	}

	@Override
	public Car getCarOut(String plate) {
		// TODO Auto-generated method stub
		return null;
	}

}
