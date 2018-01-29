package co.com.ceiba.parkinglotpaulo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.ceiba.parkinglotpaulo.domain.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
