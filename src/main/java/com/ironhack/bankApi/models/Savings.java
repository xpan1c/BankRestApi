package com.ironhack.bankApi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Entity
//@PrimaryKeyJoinColumn(name = "sId")
@Getter
public class Savings extends Account{
    private BigDecimal minimumBalance;
    private BigDecimal interesRate;

    public Savings() {
        setMinimumBalance(BigDecimal.valueOf(1000.00));
        setInteresRate(BigDecimal.valueOf(0.0025));
    }

    public Savings(BigDecimal minimumBalance, BigDecimal interesRate) {
        setMinimumBalance(minimumBalance);
        setInteresRate(interesRate);

    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        if( minimumBalance.compareTo(BigDecimal.valueOf(100.00)) >= 0) {
            this.minimumBalance = minimumBalance;
        }else {
            throw new IllegalArgumentException("Minimum balance cant be less than 100");
        }
    }

    public void setInteresRate(BigDecimal interesRate) {
        if( interesRate.compareTo(BigDecimal.valueOf(0.0025)) >= 0 & interesRate.compareTo(BigDecimal.valueOf(0.5)) <= 0) {
            this.interesRate = interesRate;
        }else {
            throw new IllegalArgumentException("Interes rate must be between 0.0025 and 0.5");
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
        if(localDate.isAfter(super.getCreationDate().plusYears(1))){
            super.increaseBalance(super.getBalance().getAmount().multiply(interesRate));
        }

    }
}
