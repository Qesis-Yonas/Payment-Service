package com.payment_service.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Customer {

    public static final String ID_NULL_VALIDATION_MESSAGE = "Customer ID must not be null";

    public static final String NAME_NULL_VALIDATION_MESSAGE = "Customer Buying name must not be null";

    public static final String EMAIL_NULL_VALIDATION_MESSAGE = "Buyer email must not be null";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;

    @NotNull(message = NAME_NULL_VALIDATION_MESSAGE)
    @Size(min = 1, message = "Buyer name must not be empty")
    private String name;

    @NotNull(message = EMAIL_NULL_VALIDATION_MESSAGE)
    @Size(min = 3)
    private String email;

}
