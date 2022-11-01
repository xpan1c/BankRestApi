package com.ironhack.bankApi.models.accounts;

import com.ironhack.bankApi.models.users.AccountHolder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;

import static java.time.temporal.ChronoUnit.DAYS;

@Entity
@Getter
public class CreditCard extends Account{
    private BigDecimal creditLimit;
    private BigDecimal interestRate;
    @Setter
    private LocalDate interestDate;
    private static final BigDecimal DEFAULT_CREDIT_LIMIT = BigDecimal.valueOf(100.00);
    private static final BigDecimal MIN_CREDIT_LIMIT = BigDecimal.valueOf(100.00);
    private static final BigDecimal MAX_CREDIT_LIMIT = BigDecimal.valueOf(100000.00);
    private static final BigDecimal DEFAULT_INTEREST_RATE = BigDecimal.valueOf(0.2);
    private static final BigDecimal MIN_INTEREST_RATE = BigDecimal.valueOf(0.1);
    private static final BigDecimal MAX_INTEREST_RATE = BigDecimal.valueOf(0.2);

    /**
     * Default CreditLimit 100.00, default InterestRate 0.2, default balance at credit limit and set interest date to the creation date.
     */
    public CreditCard() {
        setCreditLimit(DEFAULT_CREDIT_LIMIT);
        setInterestRate(DEFAULT_INTEREST_RATE);
        setBalance(DEFAULT_CREDIT_LIMIT);
        setInterestDate(super.getCreationDate());
    }

    /**
     * Set interest date to the creation date
     */
    /*
    public CreditCard(int secretKey, BigDecimal penaltyFee, BigDecimal creditLimit, BigDecimal interestRate) {
        super(secretKey, penaltyFee);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
        setBalance(creditLimit);
        this.interestDate = super.getCreationDate();
    }
    /**
     * Set interest date to the creation date
    public CreditCard(int secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, BigDecimal creditLimit, BigDecimal interestRate) {
        super(secretKey, primaryOwner, secondaryOwner, penaltyFee);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
        setBalance(creditLimit);
        this.interestDate = super.getCreationDate();
    }
     */


    /**
     *
     * @param creditLimit It has to be between 100.00 and 100000
     */
    public void setCreditLimit(BigDecimal creditLimit) {
        if( creditLimit.compareTo(MIN_CREDIT_LIMIT) >= 0
                & creditLimit.compareTo(MAX_CREDIT_LIMIT) <= 0) {
            this.creditLimit = creditLimit.setScale(2,RoundingMode.HALF_EVEN);
            this.setBalance(creditLimit);
        }else {
            throw new IllegalArgumentException("Credit limit must be between 100 and 100000");
        }
    }

    /**
     *
     * @param interestRate It has to be between 0.1 - 0.2
     */
    public void setInterestRate(BigDecimal interestRate) {
        if( interestRate.compareTo(MIN_INTEREST_RATE) >= 0
                & interestRate.compareTo(MAX_INTEREST_RATE) <= 0) {
            this.interestRate = interestRate;
        }else {
            throw new IllegalArgumentException("Interest rate must be between 0.1 and 0.2");
        }

    }

    @Override
    public void setCreationDate(LocalDate creationDate) {
        super.setCreationDate(creationDate);
        this.interestDate = creationDate;
    }

    /**
     * You can't increase Balance more than credit limit.
     * @param increase amount to increase Balance
     */
    @Override
    public void increaseBalance(BigDecimal increase) {
        if(increase.add(this.getBalance().getAmount()).compareTo(this.getCreditLimit()) <= 0) {
            super.increaseBalance(increase);
        }else{
            throw new IllegalArgumentException("You can't transfer more than "
                    + creditLimit.subtract(this.getBalance().getAmount())+".");
        }
    }

    /**
     * To get a penalty fee when balance is below zero
     */
    @Override
    public void decreaseBalance(BigDecimal decrease) {
        super.decreaseBalance(decrease);
        if(BigDecimal.ZERO.compareTo(super
                .getBalance()
                .getAmount()
                .subtract(decrease)) >= 0
        ) {
            super.decreaseBalance(getPenaltyFee());
        }
    }

    /**
     * Applies interest to balance each month.
     */
    public void addInterest(){
        LocalDate localDate = LocalDate.now(ZoneId.of("Europe/Paris"));
        if(localDate.isAfter(this.interestDate.plusMonths(1))){
            long days = DAYS.between(interestDate,localDate);
            BigDecimal decrease = creditLimit.subtract(super.getBalance().getAmount()).multiply(interestRate)
                    .divide(BigDecimal.valueOf(30),RoundingMode.HALF_EVEN);
            super.decreaseBalance(decrease.multiply(BigDecimal.valueOf(days)));
            this.setInterestDate(LocalDate.now(ZoneId.of("Europe/Paris")));
        }
    }
}
