package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.entities.concretes.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);

    void removeProduct(int id);

    void updateProduct(Product product, int id);

    Product findProductById(int id);
    List<Product> listProducts();
}
