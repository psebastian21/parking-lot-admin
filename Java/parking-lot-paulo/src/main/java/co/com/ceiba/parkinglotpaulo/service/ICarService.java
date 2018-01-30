package co.com.ceiba.parkinglotpaulo.service;

import co.com.ceiba.parkinglotpaulo.domain.Car;
import co.com.ceiba.parkinglotpaulo.utils.ParkingException;

public interface ICarService {

	public Car takeCarIn(String plate) throws ParkingException;
	
	public Car getCarOut(String plate) throws ParkingException;
	
}
