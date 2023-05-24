package kodlama.io.ecommerce.business.rules;

import kodlama.io.ecommerce.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvoiceBusinessRules {
    private final InvoiceRepository repository;

    public void checkIfExistsById(int id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Invoice not found");
        }
    }
}
