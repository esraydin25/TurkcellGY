package kodlama.io.ecommerce.api.controllers;

import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.entities.concretes.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<Product> getAll() {
        return service.listProducts();
    }

    @GetMapping("/getById")
    public Product getProductById(int id) {
        return service.findProductById(id);
    }

    @PostMapping("/addProduct")
    public void addProduct(Product product) {
        service.addProduct(product);
    }

    @DeleteMapping("/deleteProduct")
    public void deleteProduct(int id) {
        service.removeProduct(id);
    }

    @PutMapping("/updateProduct")
    public void updateProduct(Product product, int id) {
        service.updateProduct(product, id);
    }


}
