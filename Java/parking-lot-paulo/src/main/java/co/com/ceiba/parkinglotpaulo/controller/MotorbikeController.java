package co.com.ceiba.parkinglotpaulo.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.parkinglotpaulo.domain.Motorbike;
import co.com.ceiba.parkinglotpaulo.service.IMotorbikeService;
import co.com.ceiba.parkinglotpaulo.utils.ParkingException;

@RestController
@RequestMapping("/parking/motorbike")
public class MotorbikeController {
	@Autowired
	private IMotorbikeService motorbikeService;
	
	@GetMapping("/park/{plate}/{cylinderCapacity}")
	public ResponseEntity<Motorbike> parkMotorbike(@PathVariable("plate") String plate, @PathVariable("cylinderCapacity") int cylinderCapacity) throws ParseException, ParkingException{
		Motorbike motorbike = new Motorbike();
		motorbike.setCylinderCapacity(cylinderCapacity);
		motorbike.setPlate(plate);
		motorbike = motorbikeService.takeMotorbikeIn(motorbike);
		return ResponseEntity.ok().body(motorbike);
	}
	@GetMapping("out/{plate}")
	public ResponseEntity<Motorbike> getMotorbikeOut(@PathVariable("plate") String plate) throws ParkingException, ParseException{
		Motorbike motorbike = motorbikeService.getMotorbikeOut(plate);
		return ResponseEntity.ok().body(motorbike);
	}
}
