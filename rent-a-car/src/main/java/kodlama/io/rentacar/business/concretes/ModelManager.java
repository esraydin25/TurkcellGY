package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.ModelService;
import kodlama.io.rentacar.business.dto.requests.create.CreateModelRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateModelRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateModelResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllModelsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetModelResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateBrandResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateModelResponse;
import kodlama.io.rentacar.entities.Model;
import kodlama.io.rentacar.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private final ModelRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models= repository.findAll();
        List<GetAllModelsResponse> responses= models.stream().
                map(model-> mapper.map(model,GetAllModelsResponse.class)).collect(Collectors.toList());
        return responses;
    }

    @Override
    public GetModelResponse getById(int id) {
        checkIfModelExists(id);
        Model model=repository.findById(id).orElseThrow();
        GetModelResponse response=mapper.map(model,GetModelResponse.class);
        return response;
    }

    @Override
    public CreateModelResponse add(CreateModelRequest request) {
        checkIfModelExistsByName(request.getName());
        Model model=mapper.map(request,Model.class);
        model.setId(0);
        Model createdModel=repository.save(model);
        CreateModelResponse response=mapper.map(createdModel,CreateModelResponse.class);


        return response;
    }

    @Override
    public UpdateModelResponse update(int id, UpdateModelRequest request) {
        checkIfModelExists(id);
        Model model=mapper.map(request,Model.class);
        model.setId(id);
        Model crearedModel=repository.save(model);
        UpdateModelResponse response=mapper.map(crearedModel,UpdateModelResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        checkIfModelExists(id);
        repository.deleteById(id);
    }

    private void checkIfModelExists(int id){
       if(! repository.existsById(id)) throw new IllegalArgumentException("Böyle bir model bulunmamaktadır.");

    }

    private void checkIfModelExistsByName(String name){
       if(repository.existsByNameIgnoreCase(name)){
           throw  new RuntimeException("Böyle bir model zaten var");
       }
    }


}
