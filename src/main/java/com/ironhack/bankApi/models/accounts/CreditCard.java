package com.ironhack.bankApi.models.accounts;

import com.ironhack.bankApi.models.users.AccountHolder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

@Entity
//@PrimaryKeyJoinColumn(name = "cId")
@Getter
public class CreditCard extends Account{
    private BigDecimal creditLimit;
    private BigDecimal interestRate;
    @Setter
    private LocalDate interestDate;
    public CreditCard() {
        setCreditLimit(BigDecimal.valueOf(100.00));
        setInterestRate(BigDecimal.valueOf(0.2));
        setBalance(BigDecimal.valueOf(100.00));
        this.interestDate = super.getCreationDate();
    }
    public CreditCard(int secretKey, BigDecimal penaltyFee, BigDecimal creditLimit, BigDecimal interestRate) {
        super(secretKey, penaltyFee);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
        setBalance(creditLimit);
        this.interestDate = super.getCreationDate();
    }

    public CreditCard(int secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, BigDecimal creditLimit, BigDecimal interestRate) {
        super(secretKey, primaryOwner, secondaryOwner, penaltyFee);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
        setBalance(creditLimit);
        this.interestDate = super.getCreationDate();
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        if( creditLimit.compareTo(BigDecimal.valueOf(100.00)) >= 0 & creditLimit.compareTo(BigDecimal.valueOf(100000.00)) <= 0) {
            this.creditLimit = creditLimit;
        }else {
            throw new IllegalArgumentException("Credit limit must be between 100 and 100000");
        }
    }

    public void setInterestRate(BigDecimal interestRate) {
        if( interestRate.compareTo(BigDecimal.valueOf(0.1)) >= 0 & interestRate.compareTo(BigDecimal.valueOf(0.2)) <= 0) {
            this.interestRate = interestRate;
        }else {
            throw new IllegalArgumentException("Interes rate must be between 0.1 and 0.2");
        }

    }

    @Override
    public void increaseBalance(BigDecimal increase) {
        if(increase.add(this.getBalance().getAmount()).compareTo(this.getCreditLimit()) < 0) {
            super.increaseBalance(increase);
        }else{
            throw new IllegalArgumentException("You can't transfer more than "+ creditLimit.subtract(this.getBalance().getAmount())+".");
        }
    }

    /**
     * Probar
     * @param decrease
     */
    @Override
    public void decreaseBalance(BigDecimal decrease) {
        super.decreaseBalance(decrease);
        if(BigDecimal.ZERO.compareTo(super.getBalance().getAmount().subtract(decrease)) >= 0){
            super.decreaseBalance(getPenaltyFee());
        }
    }
    public void addInterest(){
        LocalDate localDate = LocalDate.now(ZoneId.of("Europe/Paris"));
        if(localDate.isAfter(this.interestDate.plusMonths(1))){
            super.decreaseBalance(creditLimit.subtract(super.getBalance().getAmount()).multiply(interestRate));
            this.setInterestDate(LocalDate.now(ZoneId.of("Europe/Paris")));
        }
    }
}
