package co.com.ceiba.parkinglotpaulo.service;

import co.com.ceiba.parkinglotpaulo.domain.Car;

public interface ICarService {

	public Car takeCarIn(String plate);
	
	public Car getCarOut(String plate);
	
}
