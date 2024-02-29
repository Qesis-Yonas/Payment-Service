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
    private Long paymentId;
    private Double paymentAmount;


    @NotNull(message = CUSTOMER_NULL_VALIDATION_MESSAGE)
    @Valid
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_customer_id")
    private Customer customer;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "credit_card_number")
    private CreditCard creditCard;

    private PaymentStatus status;

    public enum PaymentStatus {
        APPROVED, DISAPROVED;
    }

    public enum PaymentMethod {
        SLIP, CREDIT_CARD;
    }

    public PaymentMethod getType() {
        if (creditCard != null) {
            return PaymentMethod.CREDIT_CARD;
        }
        return PaymentMethod.SLIP;
    }
/*
    public enum PaymentStatus2 {
        PENDING,
        SUCCESS,
        FAILED;

        // Method to get status based on payment method
        public static PaymentStatus2 getStatus(PaymentMethod paymentMethod) {
            if (paymentMethod == PaymentMethod.SLIP) {
                return PENDING; // Payments via slip are initially pending
            } else {
                // Assuming credit card payments are immediately successful
                return SUCCESS;
            }
        }
    }
*/

//    public PaymentStatus getStatus() {
//        if (creditCard != null) {
//            return PaymentMethod.CREDIT_CARD;
//        }
//        return PaymentMethod.SLIP;
//    }


}


