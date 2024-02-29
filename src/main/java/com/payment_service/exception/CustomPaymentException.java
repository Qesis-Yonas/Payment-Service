package com.payment_service.exception;

public class CustomPaymentException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CustomPaymentException(String paymentId) {
        super(String.format("Payment with Id  %s not exists.", paymentId));
    }
}
