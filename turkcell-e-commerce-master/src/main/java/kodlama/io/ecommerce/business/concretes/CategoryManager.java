package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.CategoryService;
import kodlama.io.ecommerce.business.dto.request.create.CreateCategoryRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateCategoryRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateCategoryResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllCategoriesResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetCategoryResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateCategoryResponse;
import kodlama.io.ecommerce.entities.Category;
import kodlama.io.ecommerce.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {

    private final CategoryRepository repository;
    private final ModelMapper mapper;
    @Override
    public List<GetAllCategoriesResponse> getAll() {
        List<Category> list=repository.findAll();
        List<GetAllCategoriesResponse> responses=list.stream().
                map(category ->  mapper.map(category, GetAllCategoriesResponse.class)).
                collect(Collectors.toList());

        return responses;
    }

    @Override
    public GetCategoryResponse getById(int id) {

        Category category=repository.findById(id).orElseThrow();
        GetCategoryResponse response=mapper.map(category,GetCategoryResponse.class);
        return response;
    }

    @Override
    public CreateCategoryResponse add(CreateCategoryRequest request) {
        Category category=mapper.map(request,Category.class);
        category.setId(0);
        CreateCategoryResponse response=mapper.map(category,CreateCategoryResponse.class);
        return response;
    }

    @Override
    public UpdateCategoryResponse update(int id, UpdateCategoryRequest request) {

        return null;
    }

    @Override
    public void delete(int id) {
         repository.deleteById(id);
    }

}
