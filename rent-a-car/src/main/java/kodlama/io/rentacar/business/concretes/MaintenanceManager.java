package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.MaintenanceService;
import kodlama.io.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;
import kodlama.io.rentacar.entities.Car;
import kodlama.io.rentacar.entities.Maintenance;
import kodlama.io.rentacar.repository.CarRepository;
import kodlama.io.rentacar.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService {

    private final MaintenanceRepository repository;
    private final CarRepository carRepository;
    private final ModelMapper mapper;



    @Override
    public List<GetAllMaintenanceResponse> getAll() {
        List<Maintenance> list=repository.findAll();
        List<GetAllMaintenanceResponse> responses=list.stream()
                .map(maintenance -> mapper.map(maintenance,GetAllMaintenanceResponse.class))
                .collect(Collectors.toList());

        return responses;
    }

    @Override
    public GetMaintenanceResponse getById(int id) {
        Maintenance maintenance=repository.findById(id).orElseThrow();
        GetMaintenanceResponse response=mapper.map(maintenance,GetMaintenanceResponse.class);

        return response;
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        Maintenance maintenance=mapper.map(request,Maintenance.class);
        maintenance.setId(0);

        Maintenance createdMaintenance=repository.save(maintenance);
        Car car=carRepository.findById(request.getCar_id()).orElseThrow();

        createdMaintenance.getCars().add(car);
        car.getMaintenances().add(createdMaintenance);
        CreateMaintenanceResponse response=mapper.map(createdMaintenance,CreateMaintenanceResponse.class);

        return response;
    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        Maintenance maintenance=mapper.map(request,Maintenance.class);
        maintenance.setId(id);
        Maintenance updateMaintenance=repository.save(maintenance);


        UpdateMaintenanceResponse response=mapper.map(updateMaintenance,UpdateMaintenanceResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
      repository.deleteById(id);
    }

    private void checkIfCarByState(){

    }
}
