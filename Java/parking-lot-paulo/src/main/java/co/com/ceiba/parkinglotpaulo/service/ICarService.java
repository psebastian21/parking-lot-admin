package co.com.ceiba.parkinglotpaulo.service;

import java.text.ParseException;

import co.com.ceiba.parkinglotpaulo.domain.Car;
import co.com.ceiba.parkinglotpaulo.repository.CarRepository;
import co.com.ceiba.parkinglotpaulo.utils.ParkingException;

public interface ICarService {
	public Car takeCarIn(String plate) throws ParkingException, ParseException;
	public Car getCarOut(String plate) throws ParkingException, ParseException;
	public void setCarRepository(CarRepository carRepository);
}
