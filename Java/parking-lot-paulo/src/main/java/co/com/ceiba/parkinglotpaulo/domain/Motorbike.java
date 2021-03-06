package co.com.ceiba.parkinglotpaulo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public final class Motorbike extends Vehicle {
	@Column(nullable=false)
	private int cylinderCapacity;

	@Override
	public void calculateFee(double dailyRate, double hourlyRate) {
		super.calculateFee(dailyRate, hourlyRate);
		double bigCylinderSurcharge = (cylinderCapacity > 500) ? 2000D : 0D;
		setFee(getFee() + bigCylinderSurcharge);
	}

	public int getCylinderCapacity() {
		return cylinderCapacity;
	}

	public void setCylinderCapacity(int cylinderCapacity) {
		this.cylinderCapacity = cylinderCapacity;
	}

}
