package com.ironhack.bankApi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;
@Entity
@Getter
public class CreditCard extends Account{
    private BigDecimal creditLimit;
    private BigDecimal interesRate;

    public CreditCard() {
        setCreditLimit(BigDecimal.valueOf(100.00));
        setInteresRate(BigDecimal.valueOf(0.2));
        setBalance(BigDecimal.valueOf(100.00));
    }
    public CreditCard(int secretKey, BigDecimal penaltyFee, BigDecimal creditLimit, BigDecimal interesRate) {
        super(secretKey, penaltyFee);
        setCreditLimit(creditLimit);
        setInteresRate(interesRate);
        setBalance(creditLimit);
    }

    public CreditCard(int secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, BigDecimal creditLimit, BigDecimal interesRate) {
        super(secretKey, primaryOwner, secondaryOwner, penaltyFee);
        setCreditLimit(creditLimit);
        setInteresRate(interesRate);
        setBalance(creditLimit);
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        if( creditLimit.compareTo(BigDecimal.valueOf(100.00)) >= 0 & creditLimit.compareTo(BigDecimal.valueOf(100000.00)) <= 0) {
            this.creditLimit = creditLimit;
        }else {
            throw new IllegalArgumentException("Credit limit must be between 100 and 100000");
        }
    }

    public void setInteresRate(BigDecimal interesRate) {
        if( interesRate.compareTo(BigDecimal.valueOf(0.1)) >= 0 & interesRate.compareTo(BigDecimal.valueOf(0.2)) <= 0) {
            this.interesRate = interesRate;
        }else {
            throw new IllegalArgumentException("Interes rate must be between 0.1 and 0.2");
        }

    }
}
