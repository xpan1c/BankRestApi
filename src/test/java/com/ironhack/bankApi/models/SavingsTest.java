package com.ironhack.bankApi.models;

import com.ironhack.bankApi.models.Savings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SavingsTest {
    Savings savings = new Savings();
    @BeforeEach
    @Test
    @DisplayName("Check default saving interes rate and minimum balance")
    void savingsDefault_OK(){
        assertEquals(BigDecimal.valueOf(0.0025),savings.getInteresRate());
        assertEquals(BigDecimal.valueOf(1000),savings.getMinimumBalance());
    }
    @Test
    @DisplayName("Check if interes rate limits are ok")
    void interestRate_limits_ok(){
        savings.setInteresRate(BigDecimal.valueOf(0.5));
        assertEquals(BigDecimal.valueOf(0.5),savings.getInteresRate());
        savings.setInteresRate(BigDecimal.valueOf(0.0025));
        assertEquals(BigDecimal.valueOf(0.0025),savings.getInteresRate());
        assertThrows(IllegalArgumentException.class,() ->savings.setInteresRate(BigDecimal.valueOf(0.51)));
        assertThrows(IllegalArgumentException.class,() ->savings.setInteresRate(BigDecimal.valueOf(0.0024)));
    }
    @Test
    @DisplayName("Check if minimum Balance limits are ok")
    void minimumBalance_limits_ok(){
        savings.setMinimumBalance(BigDecimal.valueOf(101));
        assertEquals(BigDecimal.valueOf(101),savings.getMinimumBalance());
        assertThrows(IllegalArgumentException.class,() ->savings.setMinimumBalance(BigDecimal.valueOf(99)));
    }
}
