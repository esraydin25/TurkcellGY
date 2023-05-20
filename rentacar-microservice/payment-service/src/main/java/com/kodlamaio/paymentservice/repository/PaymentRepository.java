package com.kodlamaio.paymentservice.repository;

import com.kodlamaio.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
