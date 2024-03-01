package com.payment_service.service;


import com.payment_service.domain.CreditCard;
import com.payment_service.domain.Customer;
import com.payment_service.domain.Payment;
import com.payment_service.repository.CreditCardRepository;
import com.payment_service.repository.CustomerRepository;
import com.payment_service.repository.PaymentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PaymentServiceImplement implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @PersistenceContext
    private EntityManager entityManager; // Inject EntityManager


    @Transactional
    public Payment makePayment(Payment payment) {
        // Check if the Customer associated with the Payment exists
        CreditCard creditCard = payment.getCreditCard();

        if (payment.getCustomer() != null && payment.getCustomer().getCustomerId() == null) {
            // If the Customer does not exist, save it first
            Customer savedCustomer = customerRepository.save(payment.getCustomer());
            // Setting the saved Customer to the Payment
            payment.setCustomer(savedCustomer);
        }


        payment.setStatus(Payment.PaymentStatus.APPROVED);

        // Save the Payment
       return createPayment(payment);
    }


    @Transactional
    public Payment createPayment(Payment payment) {
        // Perform any necessary validation or business logic

        // For simplicity, let's assume payment.getCreditCard() returns the CreditCard to persist

        CreditCard creditCard = payment.getCreditCard();

        // If the credit card is new, save it first to ensure it's managed

        if (creditCard != null && creditCard.getNumber() != null) {
            CreditCard existingCreditCard = creditCardRepository.findByNumber(creditCard.getNumber());
            if (existingCreditCard != null) {
                // If the credit card already exists, use the existing one
                creditCard = existingCreditCard;
            } else {
                // If it doesn't exist, save the new credit card
                creditCard = creditCardRepository.save(creditCard);
            }
        }
        // Association of the managed CreditCard entity with the payment
        payment.setCreditCard(creditCard);

        // Persisting the payment
        return paymentRepository.save(payment);
    }

    public Payment getPayment(Long id) {
        Optional<Payment> payment= paymentRepository.findByPaymentId(id);
        Payment payment1 = payment.get();

        return payment1;
    }
}













// Here, assuming payment.getCreditCard() returns a CreditCard entity
// Ensure that the CreditCard entity is associated with the payment
// If it's a new CreditCard entity, ensure it's properly initialized and associated
// If it's an existing CreditCard entity, make sure it's managed or reattach it if needed