package com.ironhack.bankApi.controllers.DTOs;

import com.ironhack.bankApi.models.accounts.Savings;
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
@Setter
@NoArgsConstructor
public class SavingsDTO {
    @DecimalMin(value = "0.00")
    @NotNull
    private Double balance;
    @Digits(integer = 4,fraction = 0)
    @NotNull
    private int secretKey;
    @NotNull
    private Long primaryOwner;
    private Long secondaryOwner;
    @DecimalMin(value = "0.00")
    private Double minimumBalance;
    @DecimalMin(value = "0.00")
    private Double penaltyFee;
    @DecimalMax(value = "0.5", message = "interestRate can't be greater than 0.5")
    @DecimalMin(value = "0.0025", message = "interestRate can't be less than 0.0025")
    //@Digits(integer = 1, fraction = 4)
    private Double interestRate;
    /**
     * Constructor for test uses.
     */
    public SavingsDTO(Double balance, int secretKey, Long primaryOwner) {
        setBalance(balance);
        setSecretKey(secretKey);
        setPrimaryOwner(primaryOwner);

    }
    public Savings toSavings(){
        BigDecimal minimumBalance = BigDecimal.valueOf(Objects.requireNonNullElse(this.minimumBalance, 1000.00)).setScale(2,RoundingMode.HALF_EVEN);
        BigDecimal penaltyFee = BigDecimal.valueOf(Objects.requireNonNullElse(this.penaltyFee, 40.00)).setScale(2,RoundingMode.HALF_EVEN);
        BigDecimal interestRate = BigDecimal.valueOf(Objects.requireNonNullElse(this.interestRate, 0.0025)).setScale(4,RoundingMode.HALF_EVEN);
        BigDecimal balance = BigDecimal.valueOf(Objects.requireNonNullElse(this.balance, 1000.00)).setScale(2,RoundingMode.HALF_EVEN);
        return new Savings(balance,this.secretKey, penaltyFee, minimumBalance, interestRate);
    }
}
