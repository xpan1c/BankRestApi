package com.ironhack.bankApi.models.accounts;

import com.ironhack.bankApi.models.accounts.Savings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SavingsTest {
    Savings savings;
    @BeforeEach
    void setUp(){
        savings = new Savings();
    }
    @Test
    @DisplayName("Check default penalty")
    void penaltyDefault_OK(){
        assertEquals(BigDecimal.valueOf(40.00),savings.getPenaltyFee());
    }
    @Test
    @DisplayName("Check default saving interes rate and minimum balance")
    void savingsDefault_OK(){
        assertEquals(BigDecimal.valueOf(0.0025),savings.getInterestRate());
        assertEquals(BigDecimal.valueOf(1000.00),savings.getMinimumBalance());
    }
    @Test
    @DisplayName("Check if interes rate limits are ok")
    void interestRate_limits_ok(){
        savings.setInterestRate(BigDecimal.valueOf(0.5));
        assertEquals(BigDecimal.valueOf(0.5),savings.getInterestRate());
        savings.setInterestRate(BigDecimal.valueOf(0.0025));
        assertEquals(BigDecimal.valueOf(0.0025),savings.getInterestRate());
        assertThrows(IllegalArgumentException.class,() ->savings.setInterestRate(BigDecimal.valueOf(0.51)));
        assertThrows(IllegalArgumentException.class,() ->savings.setInterestRate(BigDecimal.valueOf(0.0024)));
    }
    @Test
    @DisplayName("Check if minimum Balance limits are ok")
    void minimumBalance_limits_ok(){
        savings.setMinimumBalance(BigDecimal.valueOf(101));
        assertEquals(BigDecimal.valueOf(101),savings.getMinimumBalance());
        assertThrows(IllegalArgumentException.class,() ->savings.setMinimumBalance(BigDecimal.valueOf(99)));
    }
    @Test
    @DisplayName("check if penalty applies when balance is less than minimum balance")
    void penaltyApplies_OK(){
        savings.setBalance(BigDecimal.valueOf(1020.00));
        savings.decreaseBalance(BigDecimal.valueOf(25.00));
        assertEquals(BigDecimal.valueOf(955.00).setScale(2),savings.getBalance().getAmount());
    }
    @Test
    @DisplayName("Check addinterest OK")
    void  addInterest(){
        savings.setCreationDate(LocalDate.of(2020,10,24));
        savings.addInterest();
        assertEquals(BigDecimal.valueOf(1005.01),savings.getBalance().getAmount());
    }
}
