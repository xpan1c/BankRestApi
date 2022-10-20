package com.ironhack.bankApi.repositories;

import com.ironhack.bankApi.models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
