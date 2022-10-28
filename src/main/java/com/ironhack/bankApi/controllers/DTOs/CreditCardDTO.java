package com.ironhack.bankApi.controllers.DTOs;

import com.ironhack.bankApi.models.accounts.CreditCard;
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
    @Setter
    @DecimalMin(value = "0.00")
    private Double penaltyFee;
    @Setter
    @DecimalMax(value = "100000.00", message = "CreditLimit can't be greater than 100000")
    @DecimalMin(value = "100.00", message = "CreditLimit can't be less than 100")
    private Double creditLimit;
    @Setter
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

    public CreditCard toCreditCard(){
        BigDecimal penaltyFee = BigDecimal.valueOf(Objects.requireNonNullElse(this.penaltyFee, 40.00)).setScale(2,RoundingMode.HALF_EVEN);
        BigDecimal interestRate = BigDecimal.valueOf(Objects.requireNonNullElse(this.interestRate, 0.20)).setScale(2,RoundingMode.HALF_EVEN);
        BigDecimal creditLimit = BigDecimal.valueOf(Objects.requireNonNullElse(this.creditLimit, 100.00)).setScale(2,RoundingMode.HALF_EVEN);
        return new CreditCard(secretKey,penaltyFee,creditLimit,interestRate);
    }
}
