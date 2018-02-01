package co.com.ceiba.parkinglotpaulo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class Vehicle{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false, name = "id")
	protected Long id;
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date entranceTime;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date exitTime;
	@Column(nullable=false)
	private String plate;
	@Column
	private Double fee;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Date getEntranceTime() {
		return entranceTime;
	}

	public void setEntranceTime(Date entranceTime) {
		this.entranceTime = entranceTime;
	}

	public Date getExitTime() {
		return exitTime;
	}

	public void setExitTime(Date exitTime) {
		this.exitTime = exitTime;
	}
	
	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}
	
	private int getElapsedHours() {
		long diffMillis = exitTime.getTime() - entranceTime.getTime();
		long diffHours = diffMillis / 3600000L;
		if (diffMillis % 3600000L >= 60000L) {
			diffHours++;
		}
		return (int) diffHours;
	}
	
	protected void calculateFee(double dailyRate, double hourlyRate) {
		int elapsedHours = this.getElapsedHours();
		int elapsedDays = elapsedHours / 24;
		elapsedHours -= (elapsedDays * 24);
		if (elapsedHours >= 9) {
			elapsedDays++;
			elapsedHours = 0;
		}
		setFee(dailyRate * elapsedDays + hourlyRate * elapsedHours);
	}
	
}
