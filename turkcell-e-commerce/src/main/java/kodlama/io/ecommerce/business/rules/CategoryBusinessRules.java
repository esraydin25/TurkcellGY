package kodlama.io.ecommerce.business.rules;

import kodlama.io.ecommerce.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryBusinessRules {
    private final CategoryRepository repository;

    public void  checkIfCategoryExistsById(int id){
        if(!repository.existsById(id)){
            throw new RuntimeException("id bulunamadı.");
        }
    }

    public void checkIfCategoryExistsByName(String name){
        if(repository.existsByNameIgnoreCase(name)){
            throw  new RuntimeException("Böyle bir categori mevcuttur.");
        }
    }

}
