package co.com.ceiba.parkinglotpaulo.dto;

import java.util.Date;

import co.com.ceiba.parkinglotpaulo.domain.Vehicle;

public class ParkedVehiclesDTO {
	private String plate;
	private String type;
	private Date entranceTime;
	public ParkedVehiclesDTO(Vehicle vehicle) {
		this.plate = vehicle.getPlate();
		this.type = vehicle.getClass().getSimpleName();
		this.entranceTime = vehicle.getEntranceTime();
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getEntranceTime() {
		return entranceTime;
	}
	public void setEntranceTime(Date entranceTime) {
		this.entranceTime = entranceTime;
	}

}
