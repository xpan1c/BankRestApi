package com.ironhack.bankApi.models.accounts;

import com.ironhack.bankApi.models.accounts.CreditCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CreditCardTest {
    CreditCard creditCard;
    @BeforeEach
    void setUp(){
        creditCard = new CreditCard();
    }
    @Test
    @DisplayName("Check penalty OK")
    void  penalty_OK(){
        creditCard.decreaseBalance(BigDecimal.valueOf(100.00));
        assertEquals(BigDecimal.valueOf(-40.00).setScale(2),creditCard.getBalance().getAmount());
    }
    @Test
    @DisplayName("Check default creditLimit & interesrate ")
    void defaultCreditLimitAndInteresRate_OK(){
        assertEquals(BigDecimal.valueOf(100.00),creditCard.getCreditLimit());
        assertEquals(BigDecimal.valueOf(0.2),creditCard.getInterestRate());
    }
    @Test
    @DisplayName("Check creditLimit limit are OK")
    void creditCardLimits_OK(){
        creditCard.setCreditLimit(BigDecimal.valueOf(100.00));
        assertEquals(BigDecimal.valueOf(100.00),creditCard.getCreditLimit());
        creditCard.setCreditLimit(BigDecimal.valueOf(100000.00));
        assertEquals(BigDecimal.valueOf(100000.00),creditCard.getCreditLimit());
        assertThrows(IllegalArgumentException.class,() -> creditCard.setCreditLimit(BigDecimal.valueOf(99.00)));
        assertThrows(IllegalArgumentException.class,() -> creditCard.setCreditLimit(BigDecimal.valueOf(100001.00)));
    }
    @Test
    @DisplayName("Check interesRate limit are OK")
    void interesRateLimits_OK(){
        creditCard.setInterestRate(BigDecimal.valueOf(0.1));
        assertEquals(BigDecimal.valueOf(0.1),creditCard.getInterestRate());
        creditCard.setInterestRate(BigDecimal.valueOf(0.2));
        assertEquals(BigDecimal.valueOf(0.2),creditCard.getInterestRate());
        assertThrows(IllegalArgumentException.class,() -> creditCard.setInterestRate(BigDecimal.valueOf(0.09)));
        assertThrows(IllegalArgumentException.class,() -> creditCard.setInterestRate(BigDecimal.valueOf(0.21)));
    }
    @Test
    @DisplayName("Check addinterest OK")
    void  addInterest(){
        creditCard.decreaseBalance(BigDecimal.valueOf(20.00));
        creditCard.setCreationDate(LocalDate.of(2022,9,24));
        creditCard.addInterest();
        assertEquals(BigDecimal.valueOf(75.88),creditCard.getBalance().getAmount());
    }

}
