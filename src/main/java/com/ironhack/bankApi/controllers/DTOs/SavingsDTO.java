package com.ironhack.bankApi.controllers.DTOs;

import com.ironhack.bankApi.models.CheckingAccount;
import com.ironhack.bankApi.models.Savings;
import com.ironhack.bankApi.models.StudentCheckingAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SavingsDTO {
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
    @DecimalMin(value = "0.00")
    private Double minimumBalance;
    @DecimalMin(value = "0.00")
    private Double penaltyFee;
    @DecimalMax(value = "0.5", message = "interestRate can't be greater than 0.5")
    @DecimalMin(value = "0.0025", message = "interestRate can't be less than 0.0025")
    private Double interestRate;
    /**
     * Constructor for test uses.
     */
    public SavingsDTO(Double balance, int secretKey, Long primaryOwner) {
        setBalance(balance);
        setSecretKey(secretKey);
        setPrimaryOwner(primaryOwner);

    }
    /**
     * Constructor for test uses.
     */
    public SavingsDTO(Double balance, int secretKey, Long primaryOwner, Double minimumBalance) {
        setBalance(balance);
        setSecretKey(secretKey);
        setPrimaryOwner(primaryOwner);
        setMinimumBalance(minimumBalance);
    }
    /**
     * Constructor for test uses.
     */
    public SavingsDTO(Double balance, int secretKey, Long primaryOwner, Double penaltyFee, Double minimumBalance) {
        setBalance(balance);
        setSecretKey(secretKey);
        setPrimaryOwner(primaryOwner);
        setMinimumBalance(minimumBalance);
        setPenaltyFee(penaltyFee);
    }




    /**
     * Null penalty fee equals to default penalty fee.
     */
    public void setPenaltyFee(Double penaltyFee) {
        this.penaltyFee = Objects.requireNonNullElse(penaltyFee, 40.00);
    }

    /**
     * Null minimum Balance equals to default penalty fee.
     */
    public void setMinimumBalance(Double minimumBalance) {
        this.minimumBalance = Objects.requireNonNullElse(minimumBalance, 1000.00);
    }
    /**
     * Null minimum Balance equals to default penalty fee.
     */
    public void setInterestRate(Double interestRate) {
        this.interestRate = Objects.requireNonNullElse(interestRate, 0.0025);
    }

    /**
     * Method to convert to Saving
     * @return Savings
     */
    public Savings toSavings(){
        Savings savings = new Savings();
        savings.setBalance(BigDecimal.valueOf(balance).setScale(2,RoundingMode.HALF_EVEN));
        savings.setSecretKey(secretKey);
        savings.setMinimumBalance(BigDecimal.valueOf(Objects.requireNonNullElse(minimumBalance, 1000.00)).setScale(2,RoundingMode.HALF_EVEN));
        savings.setPenaltyFee(BigDecimal.valueOf(penaltyFee).setScale(2,RoundingMode.HALF_EVEN));
        savings.setPenaltyFee(BigDecimal.valueOf(interestRate));
        return savings;
    }
}
