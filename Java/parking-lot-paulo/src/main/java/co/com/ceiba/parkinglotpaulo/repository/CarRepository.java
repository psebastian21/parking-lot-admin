package co.com.ceiba.parkinglotpaulo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.ceiba.parkinglotpaulo.domain.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
	
	@Query("SELECT COUNT(c) from Car c WHERE exitTime IS NULL")
	public Integer countParkedCars();

}
