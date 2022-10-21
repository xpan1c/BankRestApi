package com.ironhack.bankApi.controllers.DTOs;

import com.ironhack.bankApi.models.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    @Setter
    @DecimalMin(value = "0.00")
    @NotNull
    private Double balance;
    @Setter
    @Digits(integer = 4,fraction = 0)
    @NotNull
    private int secretKey;
    @Setter
    @NotNull
    private Long primaryOwner;
    @Setter
    private Long secondaryOwner;
    @Setter
    @DecimalMin(value = "0.00")
    private Double minimumBalance;
    @Setter
    @DecimalMin(value = "0.00")
    private Double monthlyMaintenanceFee;
    @DecimalMin(value = "0.00")
    private Double penaltyFee;

    /**
     * Constructor for test uses.
     */
    public AccountDTO(Double balance, int secretKey, Long primaryOwner) {
        setBalance(balance);
        setSecretKey(secretKey);
        setPrimaryOwner(primaryOwner);

    }
    /**
     * Constructor for test uses.
     */
    public AccountDTO(Double balance, int secretKey, Long primaryOwner, Double minimumBalance, Double monthlyMaintenanceFee) {
        setBalance(balance);
        setSecretKey(secretKey);
        setPrimaryOwner(primaryOwner);
        setMinimumBalance(minimumBalance);
        setMonthlyMaintenanceFee(monthlyMaintenanceFee);
    }
    /**
     * Constructor for test uses.
     */
    public AccountDTO(Double balance, int secretKey, Long primaryOwner, Double penaltyFee, Double minimumBalance, Double monthlyMaintenanceFee) {
        setBalance(balance);
        setSecretKey(secretKey);
        setPrimaryOwner(primaryOwner);
        setMinimumBalance(minimumBalance);
        setMonthlyMaintenanceFee(monthlyMaintenanceFee);
        setPenaltyFee(penaltyFee);
    }



    /**
     * Method to convert to a StudentCheckingAccount
     * @return StudentCheckingAccount
     */
    public StudentCheckingAccount toStudentCheckingAccount(){
        StudentCheckingAccount studentCheckingAccount = new StudentCheckingAccount();
        studentCheckingAccount.setBalance(BigDecimal.valueOf(balance).setScale(2,RoundingMode.HALF_EVEN));
        studentCheckingAccount.setSecretKey(secretKey);
        studentCheckingAccount.setPenaltyFee(BigDecimal.valueOf(penaltyFee).setScale(2,RoundingMode.HALF_EVEN));
        return studentCheckingAccount;
    }

    /**
     * Null penalty fee equals to default penalty fee.
     */
    public void setPenaltyFee(Double penaltyFee) {
        this.penaltyFee = Objects.requireNonNullElse(penaltyFee, 40.00);
    }

    /**
     * Method to convert to CheckingAccount
     * @return checkingAccount
     */
    public CheckingAccount toCheckingAccount(){

        Double minimumBalance = this.minimumBalance;
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setBalance(BigDecimal.valueOf(balance).setScale(2,RoundingMode.HALF_EVEN));
        checkingAccount.setSecretKey(secretKey);
        checkingAccount.setMinimumBalance(BigDecimal.valueOf(Objects.requireNonNullElse(minimumBalance, 250.00)).setScale(2,RoundingMode.HALF_EVEN));
        checkingAccount.setMonthlyMaintenanceFee(BigDecimal.valueOf(Objects.requireNonNullElse(monthlyMaintenanceFee, 12.00)).setScale(2,RoundingMode.HALF_EVEN));
        checkingAccount.setPenaltyFee(BigDecimal.valueOf(penaltyFee).setScale(2,RoundingMode.HALF_EVEN));
        return checkingAccount;
    }
}
