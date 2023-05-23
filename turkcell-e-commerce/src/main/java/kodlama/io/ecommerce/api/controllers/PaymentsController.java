package kodlama.io.ecommerce.api.controllers;

import kodlama.io.ecommerce.business.abstracts.PaymentService;
import kodlama.io.ecommerce.entities.Payment;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/payments")
public class PaymentsController {
    private final PaymentService service;


}
