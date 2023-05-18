package kodlama.io.ecommerce.business.rules;

import kodlama.io.ecommerce.business.abstracts.CustomerService;
import kodlama.io.ecommerce.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerBusinessRules {
    private final CustomerRepository repository;

    public void checkIfCustomerExistsById(int id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Customer_not_exists");
        }
    }

}
