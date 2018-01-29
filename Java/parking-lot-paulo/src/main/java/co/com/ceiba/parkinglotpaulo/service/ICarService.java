package co.com.ceiba.parkinglotpaulo.service;

import co.com.ceiba.parkinglotpaulo.domain.Car;

public interface ICarService {

	public Car takeCarIn(Car car);
	
	public Car getCarOut(Car car);
	
}
