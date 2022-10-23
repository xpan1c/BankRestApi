package com.ironhack.bankApi.models.accounts;

import com.ironhack.bankApi.models.users.AccountHolder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

@Entity
//@PrimaryKeyJoinColumn(name = "sId")
@Getter
public class Savings extends Account{
    private BigDecimal minimumBalance;
    private BigDecimal interestRate;
    @Setter
    private LocalDate interestDate;

    public Savings() {
        setMinimumBalance(BigDecimal.valueOf(1000.00));
        setInterestRate(BigDecimal.valueOf(0.0025));
        this.interestDate = super.getCreationDate();
    }

    public Savings(BigDecimal minimumBalance, BigDecimal interestRate) {
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
        this.interestDate = super.getCreationDate();
    }

    public Savings(BigDecimal balance, int secretKey, BigDecimal penaltyFee, BigDecimal minimumBalance, BigDecimal interestRate) {
        super(balance, secretKey, penaltyFee);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
        this.interestDate = super.getCreationDate();
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        if( minimumBalance.compareTo(BigDecimal.valueOf(100.00)) >= 0) {
            this.minimumBalance = minimumBalance;
        }else {
            throw new IllegalArgumentException("Minimum balance cant be less than 100");
        }
    }

    public void setInterestRate(BigDecimal interestRate) {
        if( interestRate.compareTo(BigDecimal.valueOf(0.0025)) >= 0 & interestRate.compareTo(BigDecimal.valueOf(0.5)) <= 0) {
            this.interestRate = interestRate;
        }else {
            throw new IllegalArgumentException("Interest rate must be between 0.0025 and 0.5");
        }
    }

    @Override
    public void decreaseBalance(BigDecimal decrease) {
        super.decreaseBalance(decrease);
        if(minimumBalance.compareTo(super.getBalance().getAmount().subtract(decrease)) >= 0){
            super.decreaseBalance(getPenaltyFee());
        }
    }
    public void addInterest(){
        LocalDate localDate = LocalDate.now(ZoneId.of("Europe/Paris"));
        if(localDate.isAfter(this.getInterestDate().plusYears(1))){
            super.increaseBalance(super.getBalance().getAmount().multiply(interestRate));
            this.setInterestDate(LocalDate.now(ZoneId.of("Europe/Paris")));
        }
    }
}
