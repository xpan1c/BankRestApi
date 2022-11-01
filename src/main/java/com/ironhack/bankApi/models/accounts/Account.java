package com.ironhack.bankApi.models.accounts;

import com.ironhack.bankApi.models.enums.Status;
import com.ironhack.bankApi.models.users.AccountHolder;
import com.ironhack.bankApi.models.utils.Money;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;

import static com.ironhack.bankApi.models.enums.Status.*;
import static java.math.RoundingMode.*;

@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Money balance;
    @Setter
    private int secretKey;
    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "primary_id")
    @Setter
    private AccountHolder primaryOwner;
    @Setter
    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "secondary_id")
    private AccountHolder secondaryOwner;
    @Setter
    private BigDecimal penaltyFee;
    @Setter
    private LocalDate creationDate;
    @Setter
    private Status status;
    private static final BigDecimal DEFAULT_PENALTY_FEE = new BigDecimal(40.00);
    /**
     * Default Penalty fee 40.00, set creation date at this time. Default Status Active
     */
    public Account() {
        setPenaltyFee(DEFAULT_PENALTY_FEE);
        setCreationDate(LocalDate.now(ZoneId.of("Europe/Paris")));
        setStatus(ACTIVE);
    }

    /**
     * Set creation date at this time. Default Status Active
     */
    public Account(int secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee) {
        setSecretKey(secretKey);
        setPrimaryOwner(primaryOwner);
        setSecondaryOwner(secondaryOwner);
        setPenaltyFee(penaltyFee);
        setCreationDate(LocalDate.now(ZoneId.of("Europe/Paris")));
        setStatus(ACTIVE);
    }
    /**
     * Set creation date at this time. Default Status Active
     */
    public Account(int secretKey, BigDecimal penaltyFee) {
        setSecretKey(secretKey);
        setPenaltyFee(penaltyFee);
        setCreationDate(LocalDate.now(ZoneId.of("Europe/Paris")));
        setStatus(ACTIVE);
    }
    /**
     * Set creation date at this time. Default Status Active
     */
    public Account(BigDecimal balance, int secretKey, BigDecimal penaltyFee) {
        setBalance(balance);
        setSecretKey(secretKey);
        setPenaltyFee(penaltyFee);
        setCreationDate(LocalDate.now(ZoneId.of("Europe/Paris")));
        setStatus(ACTIVE);
    }
    /**
     * Set creation date at this time. Default Status Active

    public Account(BigDecimal balance, int secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee) {
        setBalance(balance);
        setSecretKey(secretKey);
        setPrimaryOwner(primaryOwner);
        setSecondaryOwner(secondaryOwner);
        setPenaltyFee(penaltyFee);
        setCreationDate(LocalDate.now(ZoneId.of("Europe/Paris")));
        setStatus(Status.ACTIVE);
    }
    */
    public void setBalance(BigDecimal balance) {
        this.balance = new Money(balance);
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee.setScale(2, HALF_EVEN);
    }

    /**
     * Decrease amount balance of this account
     * @param decrease amount to Decrease Balance
     */

    public void decreaseBalance(BigDecimal decrease){
        balance.decreaseAmount(decrease.setScale(2, HALF_EVEN));
    }
    /**
     * Increase amount balance of this account
     * @param increase amount to increase Balance
     */
    public void increaseBalance(BigDecimal increase){
        balance.increaseAmount(increase.setScale(2, HALF_EVEN));
    }

}
