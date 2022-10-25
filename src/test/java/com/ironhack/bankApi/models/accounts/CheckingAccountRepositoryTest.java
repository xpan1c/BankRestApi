package com.ironhack.bankApi.models.accounts;

import com.ironhack.bankApi.models.accounts.CheckingAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CheckingAccountRepositoryTest {
    CheckingAccount checkingAccount;
    @BeforeEach
    void setUp(){
        checkingAccount = new CheckingAccount();
    }
    @Test
    @DisplayName("Check default penalty")
    void penaltyDefault_OK(){
        assertEquals(BigDecimal.valueOf(40.00),checkingAccount.getPenaltyFee());
    }
    @Test
    @DisplayName("Check default Minimum balance & MonthlyMaintenance Fee ")
    void defaultMinimumBalanceAndMaintenanceFee_OK(){
        assertEquals(BigDecimal.valueOf(250.00),checkingAccount.getMinimumBalance());
        assertEquals(BigDecimal.valueOf(12.00),checkingAccount.getMonthlyMaintenanceFee());
    }
    @Test
    @DisplayName("check if increase balance method works")
    void increaseBalance_OK(){
        checkingAccount.setBalance(BigDecimal.valueOf(270.00));
        checkingAccount.increaseBalance(BigDecimal.valueOf(25.00));
        assertEquals(BigDecimal.valueOf(295.00).setScale(2),checkingAccount.getBalance().getAmount());
    }
    @Test
    @DisplayName("check if decrease balance method works")
    void decreaseBalance_OK(){
        checkingAccount.setBalance(BigDecimal.valueOf(270.00));
        checkingAccount.decreaseBalance(BigDecimal.valueOf(5.00));
        assertEquals(BigDecimal.valueOf(265.00).setScale(2),checkingAccount.getBalance().getAmount());
    }

    @Test
    @DisplayName("check if penalty applies when balance is less than minimum balance")
    void penaltyApplies_OK(){
        checkingAccount.setBalance(BigDecimal.valueOf(270.00));
        checkingAccount.decreaseBalance(BigDecimal.valueOf(25.00));
        assertEquals(BigDecimal.valueOf(205.00).setScale(2),checkingAccount.getBalance().getAmount());
    }

}
