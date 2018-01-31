package co.com.ceiba.parkinglotpaulo.service;

import java.text.ParseException;

import co.com.ceiba.parkinglotpaulo.domain.Motorbike;
import co.com.ceiba.parkinglotpaulo.repository.MotorbikeRepository;
import co.com.ceiba.parkinglotpaulo.utils.ParkingException;

public interface IMotorbikeService {
	public Motorbike takeMotorbikeIn(Motorbike motorbike) throws ParkingException, ParseException;
	public Motorbike getMotorbikeOut(String plate) throws ParkingException, ParseException;
	public void setMotorbikeRepository(MotorbikeRepository carRepository);
}
