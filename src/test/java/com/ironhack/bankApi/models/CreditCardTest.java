package com.ironhack.bankApi.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CreditCardTest {
    CreditCard creditCard;
    @BeforeEach
    void setUp(){
        creditCard = new CreditCard();
    }
    @Test
    @DisplayName("Check default penalty")
    void penaltyDefault_OK(){
        assertEquals(BigDecimal.valueOf(40.00),creditCard.getPenaltyFee());
    }
    @Test
    @DisplayName("Check default creditLimit & interesrate ")
    void defaultCreditLimitAndInteresRate_OK(){
        assertEquals(BigDecimal.valueOf(100.00),creditCard.getCreditLimit());
        assertEquals(BigDecimal.valueOf(0.2),creditCard.getInteresRate());
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
        creditCard.setInteresRate(BigDecimal.valueOf(0.1));
        assertEquals(BigDecimal.valueOf(0.1),creditCard.getInteresRate());
        creditCard.setInteresRate(BigDecimal.valueOf(0.2));
        assertEquals(BigDecimal.valueOf(0.2),creditCard.getInteresRate());
        assertThrows(IllegalArgumentException.class,() -> creditCard.setInteresRate(BigDecimal.valueOf(0.09)));
        assertThrows(IllegalArgumentException.class,() -> creditCard.setInteresRate(BigDecimal.valueOf(0.21)));
    }

}
