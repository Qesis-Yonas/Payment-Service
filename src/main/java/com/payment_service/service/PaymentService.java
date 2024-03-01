package com.payment_service.service;

import com.payment_service.domain.Payment;

public interface PaymentService {

    public Payment makePayment(Payment payment);
    public Payment getPayment(Long id);
}
