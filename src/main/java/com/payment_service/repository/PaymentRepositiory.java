package com.payment_service.repository;

import com.payment_service.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepositiory extends JpaRepository<Payment, String> {
}
