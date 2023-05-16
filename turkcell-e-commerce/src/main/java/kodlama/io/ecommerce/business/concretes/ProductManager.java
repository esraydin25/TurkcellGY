package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.business.dto.response.create.request.create.CreateProductRequest;
import kodlama.io.ecommerce.business.dto.response.create.request.update.UpdateProductRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateProductResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllProductsResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetProductResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateProductResponse;
import kodlama.io.ecommerce.business.rules.ProductBusinessRules;
import kodlama.io.ecommerce.entities.Category;
import kodlama.io.ecommerce.entities.Product;
import kodlama.io.ecommerce.entities.enums.Status;
import kodlama.io.ecommerce.repository.CategoryRepository;
import kodlama.io.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository repository;
    private final ModelMapper mapper;
    private final ProductBusinessRules rules;

    @Override
    public List<GetAllProductsResponse> getAll() {
        List<Product> products=repository.findAll();
        List<GetAllProductsResponse> responses=products.stream().
                map(product -> mapper.map(product, GetAllProductsResponse.class)).
                collect(Collectors.toList());

        return responses;
    }

    @Override
    public GetProductResponse getById(int id) {
        Product product=repository.findById(id).orElseThrow();
        GetProductResponse response=mapper.map(product,GetProductResponse.class);
        return response;
    }

    @Override
    public CreateProductResponse add(CreateProductRequest request) {
        Product product=mapper.map(request,Product.class);
        rules.validateProduct(request.getDescription(),request.getUnitPrice(),request.getQuantity());
        product.setId(0);
        product.setStatus(Status.AVAILABLE);


        Category category=categoryRepository.findById(request.getCategoryId()).orElseThrow();
        product.getCategories().add(category);
        category.getProducts().add(product);
        repository.save(product);

        CreateProductResponse response=mapper.map(product,CreateProductResponse.class);
        return response;
    }

    @Override
    public UpdateProductResponse update(int id, UpdateProductRequest request) {
        rules.checkIfProductExistsById(id);
        rules.validateProduct(request.getDescription(),request.getUnitPrice(),request.getQuantity());
        Product product=mapper.map(request,Product.class);
        product.setId(id);
        repository.save(product);
        UpdateProductResponse response=mapper.map(product,UpdateProductResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfProductExistsById(id);
        repository.deleteById(id);
    }



}
