package com.payment_service.service;


import com.payment_service.domain.CreditCard;
import com.payment_service.domain.Customer;
import com.payment_service.domain.Payment;
import com.payment_service.repository.CreditCardRepository;
import com.payment_service.repository.CustomerRepository;
import com.payment_service.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    public Payment makePayment(Payment payment) {
        // Check if the Customer associated with the Payment exists
        if (payment.getCustomer() != null && payment.getCustomer().getCustomerId() == null) {
            // If the Customer does not exist, save it first
            Customer savedCustomer = customerRepository.save(payment.getCustomer());
            // Set the saved Customer to the Payment
            payment.setCustomer(savedCustomer);
        }
        // Save the Payment
        return paymentRepository.save(payment);
    }

    @Transactional
    public Payment createPayment(Payment payment) {
        // Perform any necessary validation or business logic

        // For simplicity, let's assume payment.getCreditCard() returns the CreditCard to persist

        CreditCard creditCard = payment.getCreditCard();

        // If the credit card is new, save it first to ensure it's managed
        if (creditCard != null && creditCard.getNumber() == null) {
            creditCard = creditCardRepository.save(creditCard);
        }

        // Associate the managed CreditCard entity with the payment
        payment.setCreditCard(creditCard);

        // Persist the payment
        return paymentRepository.save(payment);
    }
}





//        payment.setStatus(Payment.PaymentStatus.APPROVED);
//        Payment savePayment = paymentRepositiory.save(payment);
//
//        if (payment.getType().equals(Payment.PaymentMethod.SLIP)) {
//            return payment;
//        } else {
//            return savePayment;
//        }
//    }







// Here, assuming payment.getCreditCard() returns a CreditCard entity
// Ensure that the CreditCard entity is associated with the payment
// If it's a new CreditCard entity, ensure it's properly initialized and associated
// If it's an existing CreditCard entity, make sure it's managed or reattach it if needed