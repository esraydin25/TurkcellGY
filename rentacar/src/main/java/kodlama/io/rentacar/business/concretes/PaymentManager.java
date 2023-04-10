package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.PaymentService;
import kodlama.io.rentacar.business.abstracts.PosService;
import kodlama.io.rentacar.business.dto.requests.create.CreatePaymentRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdatePaymentRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreatePaymentResponse;
import kodlama.io.rentacar.business.dto.responses.get.payment.GetAllPaymentsResponse;
import kodlama.io.rentacar.business.dto.responses.get.payment.GetPaymentResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdatePaymentResponse;
import kodlama.io.rentacar.core.dto.CreateRentalPaymentRequest;
import kodlama.io.rentacar.entities.Payment;
import kodlama.io.rentacar.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {

    private final PaymentRepository repository;
    private final ModelMapper mapper;
    private final PosService posService;

    @Override
    public List<GetAllPaymentsResponse> getAll() {
        List<Payment> list=repository.findAll();
        List<GetAllPaymentsResponse> responses=list.stream().
                map(payment -> mapper.map(payment,GetAllPaymentsResponse.class)).collect(Collectors.toList());

        return responses;
    }

    @Override
    public GetPaymentResponse getById(int id) {
        checkIfPaymentExists(id);
        Payment payment=repository.findById(id).orElseThrow();
        GetPaymentResponse response=mapper.map(payment,GetPaymentResponse.class);

        return response;
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        checkIfCardExists(request);
        Payment payment=mapper.map(request,Payment.class);
        payment.setId(0);
        repository.save(payment);
        CreatePaymentResponse response=mapper.map(payment,CreatePaymentResponse.class);

        return response;
    }


    @Override
    public UpdatePaymentResponse update(int id, UpdatePaymentRequest request) {
        checkIfPaymentExists(id);
        Payment payment=mapper.map(request,Payment.class);
        payment.setId(id);
        repository.save(payment);
        UpdatePaymentResponse response=mapper.map(payment,UpdatePaymentResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        checkIfPaymentExists(id);
        repository.deleteById(id);
    }

    @Override
    public void processRentalPayment(CreateRentalPaymentRequest request) {
        checkIfPaymentIsValid(request);
        Payment payment=repository.findByCardNumber(request.getCardNumber());
        checkIfBalanceIsEnough(request.getPrice(),payment.getBalance());
        posService.pay(); // fake pos service
        payment.setBalance(payment.getBalance()-request.getPrice());
        repository.save(payment);// payment update

    }
    private void checkIfPaymentExists(int id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Ödeme bulunamadı");
        }
    }

    private void checkIfPaymentIsValid(CreateRentalPaymentRequest request) { //Geçerli Kart kontrolü
        if(!repository.existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
                request.getCardNumber(),
                request.getCardHolder(),
                request.getCardExpirationYear(),
                request.getCardExpirationMonth(),
                request.getCardCvv())){
            throw  new RuntimeException("Kart bilgileriniz hatalı");
        }
    }
    private void checkIfBalanceIsEnough(double price,double balance){
        if(balance<price){
            throw new RuntimeException("bakiye yetersiz");
        }
    }
    private void checkIfCardExists(CreatePaymentRequest request) { // Kart numarasının unique kontrolü
        if(repository.existsByCardNumber(request.getCardNumber())){
            throw new RuntimeException("Kart numarası kayıtlı");
        }
    }

}
