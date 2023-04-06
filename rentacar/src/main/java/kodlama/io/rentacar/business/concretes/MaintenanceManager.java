package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.abstracts.MaintenanceService;
import kodlama.io.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.get.car.GetCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.maintenance.GetAllMaintenancesResponse;
import kodlama.io.rentacar.business.dto.responses.get.maintenance.GetMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;
import kodlama.io.rentacar.entities.Car;
import kodlama.io.rentacar.entities.Maintenance;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;
    private final CarService carService;
    private final ModelMapper mapper;


    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> list=maintenanceRepository.findAll();
        List<GetAllMaintenancesResponse> responses=list.stream()
                .map(maintenance -> mapper.map(maintenance,GetAllMaintenancesResponse.class)).
                collect(Collectors.toList());

        return responses;
    }

    @Override
    public GetMaintenanceResponse returnCarFromMaintenance(int car_id) {
        checkIfMaintenanceExistsByCarId(car_id);

        Maintenance maintenance=maintenanceRepository.findByCarIdAndIsCompletedIsFalse(car_id);
        maintenance.setCompleted(true);
        maintenance.setEndMaintenance(LocalDateTime.now());//bakımın bitiş tarihi set edildi.
        maintenanceRepository.save(maintenance);
        carService.changeState(car_id,State.AVAILABLE);//araba bakımdan cıktı

         GetMaintenanceResponse response=mapper.map(maintenance,GetMaintenanceResponse.class);


        return response;
    }


    @Override
    public GetMaintenanceResponse getById(int id) {
        checkIfMaintenanceExistsById(id);
        Maintenance maintenance=maintenanceRepository.findById(id).orElseThrow();
        GetMaintenanceResponse response=mapper.map(maintenance,GetMaintenanceResponse.class);
        return response;
    }



    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        checkIfCarInMaintenance(request);
        checkIfCarRented(request);

        GetCarResponse carResponse=carService.getById(request.getCar_id());
        Car car=mapper.map(carResponse,Car.class);
        Maintenance maintenance=mapper.map(request,Maintenance.class);
        maintenance.setId(0);
        maintenance.setCar(car);
        maintenance.setStartMaintenance(LocalDateTime.now());
        maintenance.setEndMaintenance(null);
        maintenance.setCompleted(false);
        maintenanceRepository.save(maintenance);
        carService.changeState(request.getCar_id(), State.MAINTENANCE);

        CreateMaintenanceResponse response=mapper.map(maintenance,CreateMaintenanceResponse.class);
        response.setCar_id(car.getId());
        return response;
    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        checkIfMaintenanceExistsById(id);
        Maintenance maintenance=mapper.map(request,Maintenance.class);
        maintenance.setId(id);
        maintenanceRepository.save(maintenance);

        UpdateMaintenanceResponse response=mapper.map(maintenance,UpdateMaintenanceResponse.class);


        return response;
    }

    @Override
    public void delete(int id) {
       checkIfMaintenanceExistsById(id);
       maintenanceRepository.deleteById(id);
    }
    void checkIfMaintenanceExistsById(int id){
        if(!maintenanceRepository.existsById(id)){
            throw new RuntimeException("maintenance not found");
        }
    }
    void checkIfCarInMaintenance(CreateMaintenanceRequest request)
    {

        if(maintenanceRepository.existsByCarIdAndIsCompletedIsFalse(request.getCar_id())){
            throw new RuntimeException("Maritenance avaible");
        }
    }
    void checkIfCarRented(CreateMaintenanceRequest request){
        GetCarResponse response=carService.getById(request.getCar_id());
        if(response.getState().equals(State.RENTED)){
            throw new RuntimeException("rented car cannot maintenance");
        }
    }
    private void checkIfMaintenanceExistsByCarId(int car_id) {
        if(!maintenanceRepository.existsByCarIdAndIsCompletedIsFalse(car_id)){
            throw new RuntimeException("car not found");
        }
    }


}
