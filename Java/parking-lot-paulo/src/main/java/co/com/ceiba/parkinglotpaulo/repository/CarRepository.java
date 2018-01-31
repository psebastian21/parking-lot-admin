package co.com.ceiba.parkinglotpaulo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.ceiba.parkinglotpaulo.domain.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
	@Query("SELECT COUNT(c) FROM Car c WHERE exitTime IS NULL")
	public Integer countParkedCars();
	@Query("SELECT c FROM Car c WHERE plate = :plate AND exitTime IS NULL")
	public Car checkIfCarIsAlreadyParked(@Param("plate") String plate);
}
