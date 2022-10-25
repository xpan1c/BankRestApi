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
//@PrimaryKeyJoinColumn(name = "sId")
@Getter
public class Savings extends Account{
    private BigDecimal minimumBalance;
    private BigDecimal interestRate;
    @Setter
    private LocalDate interestDate;

    /**
     * Default MinimumBalance 1000.00, default InterestRate 0.0025
     */
    public Savings() {
        super.setBalance(BigDecimal.valueOf(1000.00));
        setMinimumBalance(BigDecimal.valueOf(1000.00));
        setInterestRate(BigDecimal.valueOf(0.0025));
        this.interestDate = super.getCreationDate();
    }
    /**
     * Set interest date to the creation date
     */
    public Savings(BigDecimal minimumBalance, BigDecimal interestRate) {
        super.setBalance(minimumBalance);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
        this.interestDate = super.getCreationDate();
    }
    /**
     * Set interest date to the creation date
     */
    public Savings(BigDecimal balance, int secretKey, BigDecimal penaltyFee, BigDecimal minimumBalance, BigDecimal interestRate) {
        super(balance, secretKey, penaltyFee);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
        this.interestDate = super.getCreationDate();
    }

    /**
     *
     * @param minimumBalance Greater or equals than 100.00
     */
    public void setMinimumBalance(BigDecimal minimumBalance) {
        if( minimumBalance.compareTo(BigDecimal.valueOf(100.00)) >= 0) {
            this.minimumBalance = minimumBalance;
        }else {
            throw new IllegalArgumentException("Minimum balance cant be less than 100");
        }
    }

    /**
     *
     * @param interestRate It has to be between 0.0025 and 0.5
     */
    public void setInterestRate(BigDecimal interestRate) {
        if( interestRate.compareTo(BigDecimal.valueOf(0.0025)) >= 0 & interestRate.compareTo(BigDecimal.valueOf(0.5)) <= 0) {
            this.interestRate = interestRate;
        }else {
            throw new IllegalArgumentException("Interest rate must be between 0.0025 and 0.5");
        }
    }

    @Override
    public void setCreationDate(LocalDate creationDate) {
        super.setCreationDate(creationDate);
        this.interestDate = creationDate;
    }

    /**
     * Applies penalty fee
     * @param decrease amount to Decrease Balance
     */
    @Override
    public void decreaseBalance(BigDecimal decrease) {
        super.decreaseBalance(decrease);
        if(minimumBalance.compareTo(super.getBalance().getAmount().subtract(decrease)) >= 0){
            super.decreaseBalance(getPenaltyFee());
        }
    }

    /**
     * Applies interest every year
     */
    public void addInterest(){
        LocalDate localDate = LocalDate.now(ZoneId.of("Europe/Paris"));
        if(localDate.isAfter(this.getInterestDate().plusYears(1))){
            long days = DAYS.between(interestDate,localDate);
            BigDecimal decrease = super.getBalance().getAmount().multiply(interestRate).divide(BigDecimal.valueOf(365),RoundingMode.HALF_EVEN);
            super.increaseBalance(decrease.multiply(BigDecimal.valueOf(days)));
            this.setInterestDate(LocalDate.now(ZoneId.of("Europe/Paris")));
        }
    }
}
