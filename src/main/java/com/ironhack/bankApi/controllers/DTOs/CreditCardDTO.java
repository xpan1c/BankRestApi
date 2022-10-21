package com.ironhack.bankApi.controllers.DTOs;

import com.ironhack.bankApi.models.CheckingAccount;
import com.ironhack.bankApi.models.CreditCard;
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
public class CreditCardDTO {

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
    private Double penaltyFee;
    @DecimalMax(value = "100000.00", message = "CreditLimit can't be greater than 100000")
    @DecimalMin(value = "100.00", message = "CreditLimit can't be less than 100")
    private Double creditLimit;
    @DecimalMax(value = "0.2", message = "interestRate can't be greater than 0.2")
    @DecimalMin(value = "0.1", message = "interestRate can't be less than 0.1")
    private Double interestRate;
    /**
     * Constructor for test uses.
     */
    public CreditCardDTO(int secretKey, Long primaryOwner) {
        setSecretKey(secretKey);
        setPrimaryOwner(primaryOwner);
    }

    /**
     * Constructor for test uses.
     */
    public CreditCardDTO(int secretKey, Long primaryOwner, Double penaltyFee) {
        setSecretKey(secretKey);
        setPrimaryOwner(primaryOwner);
        setPenaltyFee(penaltyFee);
    }
    /**
     * Constructor for test uses.
     */
    public CreditCardDTO(int secretKey, Long primaryOwner, Double penaltyFee,Double creditLimit) {
        setSecretKey(secretKey);
        setPrimaryOwner(primaryOwner);
        setPenaltyFee(penaltyFee);
        setCreditLimit(creditLimit);
    }


    /**
     * Null penalty fee equals to default penalty fee.
     */
    public void setPenaltyFee(Double penaltyFee) {
        this.penaltyFee = Objects.requireNonNullElse(penaltyFee, 40.00);
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = Objects.requireNonNullElse(creditLimit, 100.00);
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = Objects.requireNonNullElse(interestRate, 0.20);
    }

    public CreditCard toCreditCard(){
        BigDecimal penaltyFee = BigDecimal.valueOf(this.penaltyFee).setScale(2,RoundingMode.HALF_EVEN);
        BigDecimal creditLimit = BigDecimal.valueOf(this.creditLimit).setScale(2,RoundingMode.HALF_EVEN);
        BigDecimal interestRate = BigDecimal.valueOf(this.interestRate);
        return new CreditCard(secretKey,penaltyFee,creditLimit,interestRate);
    }
}
