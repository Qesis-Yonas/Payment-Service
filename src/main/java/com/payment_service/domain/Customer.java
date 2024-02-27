package com.payment_service.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    public static final String ID_NULL_VALIDATION_MESSAGE = "Customer ID must not be null";

    public static final String NAME_NULL_VALIDATION_MESSAGE = "Customer Buying name must not be null";

    public static final String EMAIL_NULL_VALIDATION_MESSAGE = "Buyer email must not be null";

    public static final String CPF_NULL_VALIDATION_MESSAGE = "Buyer cpf must not be null";

    @Id
    @GeneratedValue
    private String customerId;

}
