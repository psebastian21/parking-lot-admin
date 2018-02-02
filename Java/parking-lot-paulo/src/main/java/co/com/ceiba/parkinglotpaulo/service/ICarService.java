package co.com.ceiba.parkinglotpaulo.service;

import java.text.ParseException;
import java.util.List;

import co.com.ceiba.parkinglotpaulo.domain.Car;
import co.com.ceiba.parkinglotpaulo.repository.CarRepository;
import co.com.ceiba.parkinglotpaulo.utils.ITimeSource;
import co.com.ceiba.parkinglotpaulo.utils.ParkingException;

public interface ICarService {
	public Car takeCarIn(String plate) throws ParkingException, ParseException;
	public Car getCarOut(String plate) throws ParkingException, ParseException;
	public void setCarRepository(CarRepository carRepository);
	public ITimeSource getTimeSource();
	public void setTimeSource(ITimeSource timeSource);
	public List<Car> findAllParkedCars();
}
