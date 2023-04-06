package kodlama.io.rentacar.repository;

import kodlama.io.rentacar.business.dto.responses.get.car.GetAllCarResponse;
import kodlama.io.rentacar.entities.Car;
import kodlama.io.rentacar.entities.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {
    boolean  existsByPlateIgnoreCase(String plate);
    List<Car> findAllByStateIsNot(State state);

}
