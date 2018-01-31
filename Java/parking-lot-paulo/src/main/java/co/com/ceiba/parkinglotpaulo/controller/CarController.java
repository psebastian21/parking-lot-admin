package co.com.ceiba.parkinglotpaulo.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.parkinglotpaulo.domain.Car;
import co.com.ceiba.parkinglotpaulo.service.ICarService;
import co.com.ceiba.parkinglotpaulo.utils.ParkingException;

@RestController
@RequestMapping("/api/car")
public class CarController {
	@Autowired
	private ICarService carService;
	
	@GetMapping("/park/{plate}")
	public ResponseEntity<Car> parkCar(@PathVariable("plate") String plate) throws ParseException, ParkingException{
		Car car = carService.takeCarIn(plate);
		return ResponseEntity.ok().body(car);
	}
	@GetMapping("out/{plate}")
	public ResponseEntity<Car> getCarOut(@PathVariable("plate") String plate) throws ParkingException, ParseException{
		Car car = carService.getCarOut(plate);
		return ResponseEntity.ok().body(car);
	}

}
