package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.dto.requests.create.CreateCarRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateCarResponse;
import kodlama.io.rentacar.entities.Car;
import kodlama.io.rentacar.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private CarRepository repository;
    private ModelMapper mapper;
    @Override
    public List<GetAllCarResponse> getAll() {
        List<Car> cars=repository.findAll();
        List<GetAllCarResponse> responses=cars.stream().
                map(car -> mapper.map(car,GetAllCarResponse.class)).collect(Collectors.toList());
        return responses;
    }

    @Override
    public GetCarResponse getById(int id) {
        checkIfCarExists(id);
        Car car=repository.findById(id).orElseThrow();
        GetCarResponse response=mapper.map(car,GetCarResponse.class);
        return response;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        checkIfCarExistsByPlate(request.getPlate());
        Car car=mapper.map(request,Car.class);
        car.setId(0);
        repository.save(car);
        CreateCarResponse response=mapper.map(car,CreateCarResponse.class);
        return response;
    }

    @Override
    public UpdateCarResponse update(int id, UpdateCarRequest request) {
        checkIfCarExists(id);
        Car car=mapper.map(request,Car.class);
        car.setId(id);
        repository.save(car);
        UpdateCarResponse response=mapper.map(car,UpdateCarResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
           checkIfCarExists(id);
           repository.deleteById(id);
    }
    private void checkIfCarExists(int id){
        if(!repository.existsById(id)){
            throw new IllegalArgumentException("Böyle bir araba bulunmamaktadır");
        }
    }
    private void checkIfCarExistsByPlate(String plate){
        if(repository.existsByPlateIgnoreCase(plate)){
            throw new RuntimeException("Böyle bir plaka sistemde mevcut.");
        }
    }
}
