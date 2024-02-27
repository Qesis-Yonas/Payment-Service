package com.payment_service.domain;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import com.payment_service.domain.PaymentMethod;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    public static final String PAYMENT_AMOUNT_NULL_VALIDATION_MESSAGE = "Payment Amount can not be null";
    public static final String CUSTOMER_NULL_VALIDATION_MESSAGE = "Customer can not be null";

    @Id
    @GeneratedValue
    private String paymentId;

    @NotNull(message = PAYMENT_AMOUNT_NULL_VALIDATION_MESSAGE)
    private Double paymentAmount;

    private PaymentMethod paymentType;

    @NotNull(message = CUSTOMER_NULL_VALIDATION_MESSAGE)
    @Valid
    @ManyToOne
    private Customer customer;

    @Valid
    @ManyToOne
    private CreditCard creditCard;

    public enum PaymentStatus {
        APPROVED;
    }

    public PaymentMethod getType() {
        if (creditCard != null) {
            return PaymentMethod.CREDIT_CARD;
        }
        return PaymentMethod.SLIP;
    }

    public enum PaymentMethod {
        SLIP, CREDIT_CARD;
    }

}

