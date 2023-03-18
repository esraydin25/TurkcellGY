package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.CheckProductService;
import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.entities.concretes.Product;
import kodlama.io.ecommerce.repository.abstracts.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements ProductService {

    private ProductRepository repository;
    private CheckProductService service;

    public ProductManager(ProductRepository repository, CheckProductService service) {

        this.repository = repository;
        this.service = service;
    }

    @Override
    public void addProduct(Product product) {

        if (!service.isValid(product)) {

            throw new RuntimeException("hatalı kayit");
        } else {

            repository.addProduct(product);
            System.out.println("ürün basariyla eklendi");
        }


    }

    @Override
    public void removeProduct(int id) {
        if (repository.listProducts().size() == 0) {
            throw new RuntimeException("ürün yok");
        } else if (repository.findProductById(id) == null) {
            throw new RuntimeException("ürün bulunamadi");

        } else {
            repository.removeProduct(id);
            System.out.println("ürün basariyla silindi");
        }


    }

    @Override
    public void updateProduct(Product product, int id) {
        if (repository.listProducts().size() == 0) {
            throw new RuntimeException("ürün yok");
        } else if (repository.findProductById(id) == null) {
            throw new RuntimeException("bu id ile ürün bulunamadi");
        } else if (!service.isValid(product)) {
            throw new RuntimeException("hatalı ürün bilgisi girdiniz, güncelleme yapilamadi");
        } else {
            repository.updateProduct(product, id);
            System.out.println("ürün basariyla güncellendi");
        }


    }

    @Override
    public Product findProductById(int id) {

        if (repository.findProductById(id) == null) {
            throw new RuntimeException("ürün bulunamadi");
        }

        return repository.findProductById(id);

    }

    @Override
    public List<Product> listProducts() {
        if (repository.listProducts().size() == 0) {
            throw new RuntimeException("ürün listesi bos");
        }
        return repository.listProducts();
    }
}
