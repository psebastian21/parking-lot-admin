package co.com.ceiba.parkinglotpaulo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.ceiba.parkinglotpaulo.domain.Motorbike;

@Repository
public interface MotorbikeRepository extends JpaRepository<Motorbike, Long>{
	@Query("SELECT COUNT(m) FROM Motorbike m WHERE exitTime IS NULL")
	public Integer countParkedMotorbikes();
	@Query("SELECT m FROM Motorbike m WHERE plate = :plate AND exitTime IS NULL")
	public Motorbike checkIfMotorbikeIsAlreadyParked(@Param("plate") String plate);
}
