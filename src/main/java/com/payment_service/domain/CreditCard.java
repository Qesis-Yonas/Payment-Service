package com.payment_service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {

    @NotNull(message = "Credit Card number must not be null.")
    @Size(min=16, message="Credit Card number must not be empty.")
    @Id
    @GeneratedValue
    private String number;

    @NotNull(message = "Credit card holder name must not be null.")
    @Size(min=1, message="Credit card holder name must not be empty.")
    private String holderName;

    @NotNull(message = "Credit Card expiration date must not be null.")
    @Size(min=1, message="Credit Card expiration date must not be empty.")
    private String expirationDate;

    @NotNull(message = "Credit Card CVV must not be null.")
    @Size(min=3, message="Credit Card CVV must not be empty.")
    private String cvv;

}
