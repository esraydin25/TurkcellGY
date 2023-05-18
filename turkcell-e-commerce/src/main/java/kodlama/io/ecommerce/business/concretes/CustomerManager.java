package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.CustomerService;
import kodlama.io.ecommerce.business.dto.request.create.CreateCustomerRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateCustomerRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateCustomerResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllCustomersResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetCustomerResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateCustomerResponse;
import kodlama.io.ecommerce.business.rules.CustomerBusinessRules;
import kodlama.io.ecommerce.entities.Customer;
import kodlama.io.ecommerce.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;


@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
    private final CustomerRepository repository;
    private final ModelMapper mapper;
    private final CustomerBusinessRules rules;
    @Override
    public List<GetAllCustomersResponse> getAll() {
        List<Customer> customers=repository.findAll();
        List<GetAllCustomersResponse> responses=customers.stream()
                .map(customer -> mapper.map(customer,GetAllCustomersResponse.class))
                .toList();

        return responses;
    }

    @Override
    public GetCustomerResponse getById(int id) {
        rules.checkIfCustomerExistsById(id);
        Customer customer=repository.findById(id).orElseThrow();
        GetCustomerResponse response=mapper.map(customer,GetCustomerResponse.class);

        return response;
    }

    @Override
    public CreateCustomerResponse add(CreateCustomerRequest request) {
        Customer customer=mapper.map(request,Customer.class);
        customer.setId(0);
        repository.save(customer);
        CreateCustomerResponse response=mapper.map(customer,CreateCustomerResponse.class);

        return response;
    }

    @Override
    public UpdateCustomerResponse update(int id, UpdateCustomerRequest request) {
        rules.checkIfCustomerExistsById(id);
        Customer customer=mapper.map(request,Customer.class);
        customer.setId(id);
        repository.save(customer);
        UpdateCustomerResponse response=mapper.map(customer,UpdateCustomerResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfCustomerExistsById(id);
        repository.deleteById(id);

    }
}
