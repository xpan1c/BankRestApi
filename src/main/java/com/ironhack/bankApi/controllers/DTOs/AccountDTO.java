package com.ironhack.bankApi.controllers.DTOs;

import com.ironhack.bankApi.models.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    @DecimalMin(value = "0.00")
    @NotNull
    private BigDecimal balance;
    @Digits(integer = 4,fraction = 0)
    @NotNull
    private int secretKey;
    @NotNull
    private Long primaryOwner;
    private Long secondaryOwner;
    @NotNull
    @DecimalMin(value = "0.00")
    private BigDecimal minimumBalance;
    @NotNull
    @DecimalMin(value = "0.00")
    private BigDecimal monthlyMaintenanceFee;
    public CheckingAccount toCheckingAccount(){
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setBalance(balance);
        checkingAccount.setSecretKey(secretKey);
        checkingAccount.setMinimumBalance(minimumBalance);
        checkingAccount.setMonthlyMaintenanceFee(monthlyMaintenanceFee);
        return checkingAccount;
    }

    public AccountDTO(BigDecimal balance, int secretKey, Long primaryOwner, BigDecimal minimumBalance, BigDecimal monthlyMaintenanceFee) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }


    public StudentCheckingAccount toStudentCheckingAccount(){
        StudentCheckingAccount studentCheckingAccount = new StudentCheckingAccount();
        studentCheckingAccount.setBalance(balance);
        studentCheckingAccount.setSecretKey(secretKey);
        return studentCheckingAccount;
    }
}
