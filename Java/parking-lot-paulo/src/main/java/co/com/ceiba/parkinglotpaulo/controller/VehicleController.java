package co.com.ceiba.parkinglotpaulo.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.parkinglotpaulo.domain.Vehicle;
import co.com.ceiba.parkinglotpaulo.dto.ParkedVehiclesDTO;
import co.com.ceiba.parkinglotpaulo.service.ICarService;
import co.com.ceiba.parkinglotpaulo.service.IMotorbikeService;

@RestController
@RequestMapping("/parking/vehicle")
public class VehicleController {
	@Autowired
	private ICarService carService;
	@Autowired
	private IMotorbikeService motorbikeService;
	
	@GetMapping("/list")
	public ResponseEntity<List<ParkedVehiclesDTO>> listParkedVehicles(){
		List<Vehicle> parkedVehicles = new LinkedList<Vehicle>(carService.findAllParkedCars());
		parkedVehicles.addAll(motorbikeService.findAllParkedMotorbikes());
		List<ParkedVehiclesDTO> response = new LinkedList<ParkedVehiclesDTO>();
		for(Vehicle v : parkedVehicles) {
			response.add(new ParkedVehiclesDTO(v));
		}
		return ResponseEntity.ok(response);
	}
}
