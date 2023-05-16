package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.response.create.request.create.CreateProductRequest;
import kodlama.io.ecommerce.business.dto.response.create.request.update.UpdateProductRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateProductResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllProductsResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetProductResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateProductResponse;

import java.util.List;

public interface ProductService {
    List<GetAllProductsResponse> getAll();

    GetProductResponse getById(int id);

    CreateProductResponse add(CreateProductRequest request);
    UpdateProductResponse update(int id, UpdateProductRequest request);
    void delete(int id);

}
