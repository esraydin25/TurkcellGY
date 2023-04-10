package kodlama.io.rentacar.business.dto.requests.create;

import kodlama.io.rentacar.business.dto.requests.PaymentRequest;
import kodlama.io.rentacar.core.dto.CreateRentalPaymentRequest;
import kodlama.io.rentacar.entities.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalRequest {
    private int carId;
    private double dailyPrice;
    private int rentedForDays;
    private PaymentRequest paymentRequest;


}


