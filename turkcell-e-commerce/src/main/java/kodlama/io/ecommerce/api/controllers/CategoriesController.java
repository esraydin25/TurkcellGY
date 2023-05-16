package kodlama.io.ecommerce.api.controllers;

import kodlama.io.ecommerce.business.abstracts.CategoryService;
import kodlama.io.ecommerce.business.dto.response.create.request.create.CreateCategoryRequest;
import kodlama.io.ecommerce.business.dto.response.create.request.update.UpdateCategoryRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateCategoryResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllCategoriesResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllProductsResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetCategoryResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetProductResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateCategoryResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoriesController {
   private CategoryService service;


    @GetMapping
    public List<GetAllCategoriesResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public GetCategoryResponse getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCategoryResponse add(@RequestBody CreateCategoryRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateCategoryResponse update(@PathVariable int id, @RequestBody UpdateCategoryRequest request){
        return service.update(id,request);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }

}
