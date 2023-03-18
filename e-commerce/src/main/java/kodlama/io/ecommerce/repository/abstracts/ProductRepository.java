package kodlama.io.ecommerce.repository.abstracts;


import kodlama.io.ecommerce.entities.concretes.Product;

import java.util.List;

public interface ProductRepository {

    void addProduct(Product product);

    void removeProduct(int id);

    void updateProduct(Product product, int id);

    Product findProductById(int id);

    List<Product> listProducts();
}
