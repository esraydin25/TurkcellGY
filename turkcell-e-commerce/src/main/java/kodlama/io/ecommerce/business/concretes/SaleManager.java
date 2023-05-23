package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.PaymentService;
import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.business.abstracts.SaleService;
import kodlama.io.ecommerce.common.dto.CreateSalePaymentRequest;
import kodlama.io.ecommerce.business.dto.request.create.CreateSaleRequest;
import kodlama.io.ecommerce.business.dto.request.update.UpdateSaleRequest;
import kodlama.io.ecommerce.business.dto.response.create.CreateSaleResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetAllSalesResponse;
import kodlama.io.ecommerce.business.dto.response.get.GetSaleResponse;
import kodlama.io.ecommerce.business.dto.response.update.UpdateSaleResponse;
import kodlama.io.ecommerce.business.rules.SaleBusinessRules;
import kodlama.io.ecommerce.entities.Sale;
import kodlama.io.ecommerce.repository.SaleRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class SaleManager implements SaleService {
    private final SaleRepository repository;
    private final ModelMapper mapper;
    private final SaleBusinessRules rules;
    private final ProductService productService;
    private final PaymentService paymentService;


    @Override
    public List<GetAllSalesResponse> getAll() {
        List<Sale> sales=repository.findAll();
        List<GetAllSalesResponse> responses=sales
                .stream()
                .map(sale -> mapper.map(sale, GetAllSalesResponse.class)).toList();

        return responses;
    }

    @Override
    public GetSaleResponse getById(int id) {
        rules.checkIfSaleExistsById(id);
        Sale sale=repository.findById(id).orElseThrow();
        GetSaleResponse response=mapper.map(sale,GetSaleResponse.class);

        return response;
    }

    @Override
    public CreateSaleResponse add(CreateSaleRequest request) {
        double totalPrice=0;
       List<Integer> productIds=request.getProductIds();
       for(Integer i:productIds){
           rules.checkIfProductExistsByStatus(i);
           rules.checkIfProductExistsByQuantity(i);
           totalPrice+=productService.getById(i).getUnitPrice();
       }
        Sale sale=mapper.map(request,Sale.class);
        sale.setId(0);
        sale.setCreatedAt(LocalDate.now());
        sale.setTotalPrice(totalPrice);
        //payments
        CreateSalePaymentRequest salePaymentRequest=new CreateSalePaymentRequest();
        mapper.map(request.getPaymentRequest(),salePaymentRequest);
        salePaymentRequest.setPrice(totalPrice);
        paymentService.processSalePayment(salePaymentRequest);




        repository.save(sale);
        CreateSaleResponse response=mapper.map(sale,CreateSaleResponse.class);

        return response;
    }

    @Override
    public UpdateSaleResponse update(int id, UpdateSaleRequest request) {
        rules.checkIfSaleExistsById(id);
        Sale sale=mapper.map(request,Sale.class);
        sale.setId(id);
        repository.save(sale);
        UpdateSaleResponse response=mapper.map(sale,UpdateSaleResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfSaleExistsById(id);
        repository.deleteById(id);
    }
}
