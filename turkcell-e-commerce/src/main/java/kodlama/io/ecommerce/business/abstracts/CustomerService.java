package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.request.create.CreateCustomerRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateCustomerRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateCustomerResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllCustomersResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetCustomerResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateCustomerResponse;

import java.util.List;

public interface CustomerService {
    List<GetAllCustomersResponse> getAll();
    GetCustomerResponse getById(int id);
    CreateCustomerResponse add(CreateCustomerRequest request);
    UpdateCustomerResponse update(int id, UpdateCustomerRequest request);
    void delete(int id);

}
