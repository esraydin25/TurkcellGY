package kodlama.io.ecommerce.repository.concretes;

import kodlama.io.ecommerce.entities.concretes.Product;
import kodlama.io.ecommerce.repository.abstracts.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    List<Product> products;

    public InMemoryProductRepository() {
        products = new ArrayList<>();
        products.add(new Product(1, "IPhone 14", 1, 33.000, "phone"));
        products.add(new Product(2, "Monster Abra A5 V19.2", 1, 20.250, "gaming computer"));
        products.add(new Product(3, "LG OLED LED TV", 1, 25.175, "led tv"));
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public void removeProduct(int id) {
        for (Product product : products) {
            if (product.getId() == id)
                products.remove(product);
        }
    }

    @Override
    public void updateProduct(Product product, int id) {
        for (Product product1 : products) {
            if (product1.getId() == id) {
                product1.setDescription(product.getDescription());
                product1.setName(product.getName());
                product1.setPrice(product.getPrice());
                product1.setQuantity(product.getQuantity());
            }
        }
    }

    @Override
    public Product findProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id)
                return product;
        }
        return null;
    }

    @Override
    public List<Product> listProducts() {
        return products;
    }
}
