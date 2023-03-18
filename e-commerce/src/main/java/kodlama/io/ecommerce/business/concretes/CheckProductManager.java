package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.CheckProductService;
import kodlama.io.ecommerce.entities.concretes.Product;
import org.springframework.stereotype.Service;

@Service
public class CheckProductManager implements CheckProductService {
    @Override
    public boolean isValid(Product product) {
        if (product.getPrice() <= 0
                || product.getQuantity() <= 0
                || (product.getDescription().length() < 10 || product.getDescription().length() > 50)) {
            return false;
        } else {
            return true;
        }

    }
}
