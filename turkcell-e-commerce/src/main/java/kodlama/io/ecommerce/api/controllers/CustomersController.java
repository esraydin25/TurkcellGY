package kodlama.io.ecommerce.api.controllers;

import jakarta.validation.Valid;
import kodlama.io.ecommerce.business.abstracts.CustomerService;
import kodlama.io.ecommerce.business.dto.request.create.CreateCustomerRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateCustomerRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateCustomerResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllCustomersResponse;

import kodlama.io.ecommerce.business.dto.response.get.GetCustomerResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateCustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customers")
public class CustomersController {
    private final CustomerService service;

    @GetMapping
    public List<GetAllCustomersResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public GetCustomerResponse getById(@PathVariable int id){
        return service.getById(id);
    }
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCustomerResponse add(@Valid @RequestBody CreateCustomerRequest request){
        return service.add(request);
    }
    @PutMapping("/{id}")
    public UpdateCustomerResponse update(@PathVariable int id, @Valid @RequestBody UpdateCustomerRequest request){
        return service.update(id,request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }

}
