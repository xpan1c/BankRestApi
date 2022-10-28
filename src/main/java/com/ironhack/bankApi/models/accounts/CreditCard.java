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

    /**
     * Default CreditLimit 100.00, default InterestRate 0.2, default balance at credit limit and set interest date to the creation date.
     */
    public CreditCard() {
        setCreditLimit(BigDecimal.valueOf(100.00));
        setInterestRate(BigDecimal.valueOf(0.2));
        setBalance(BigDecimal.valueOf(100.00));
        this.interestDate = super.getCreationDate();
    }

    /**
     * Set interest date to the creation date
     */
    public CreditCard(int secretKey, BigDecimal penaltyFee, BigDecimal creditLimit, BigDecimal interestRate) {
        super(secretKey, penaltyFee);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
        setBalance(creditLimit);
        this.interestDate = super.getCreationDate();
    }
    /**
     * Set interest date to the creation date
     */
    public CreditCard(int secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, BigDecimal creditLimit, BigDecimal interestRate) {
        super(secretKey, primaryOwner, secondaryOwner, penaltyFee);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
        setBalance(creditLimit);
        this.interestDate = super.getCreationDate();
    }

    /**
     *
     * @param creditLimit It has to be between 100.00 and 100000
     */
    public void setCreditLimit(BigDecimal creditLimit) {
        if( creditLimit.compareTo(BigDecimal.valueOf(100.00)) >= 0
                & creditLimit.compareTo(BigDecimal.valueOf(100000.00)) <= 0) {
            this.creditLimit = creditLimit;
        }else {
            throw new IllegalArgumentException("Credit limit must be between 100 and 100000");
        }
    }

    /**
     *
     * @param interestRate It has to be between 0.1 - 0.2
     */
    public void setInterestRate(BigDecimal interestRate) {
        if( interestRate.compareTo(BigDecimal.valueOf(0.1)) >= 0
                & interestRate.compareTo(BigDecimal.valueOf(0.2)) <= 0) {
            this.interestRate = interestRate;
        }else {
            throw new IllegalArgumentException("Interes rate must be between 0.1 and 0.2");
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
        if(BigDecimal.ZERO.compareTo(super.getBalance().getAmount().subtract(decrease)) >= 0){
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
