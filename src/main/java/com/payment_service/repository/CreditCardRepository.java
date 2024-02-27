package com.payment_service.repository;

import com.payment_service.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    public CreditCard findByNumber(Long number);
}
