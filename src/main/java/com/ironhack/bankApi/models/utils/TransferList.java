package com.ironhack.bankApi.models.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.bankApi.models.accounts.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TransferList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "from_id")
    @JsonIgnore
    private Account from;
    @ManyToOne
    @JoinColumn(name = "to_id")
    @JsonIgnore
    private Account to;
    private BigDecimal quantity;
    private LocalDateTime currentOperation;

    public TransferList(Account from, Account to, BigDecimal quantity) {
        setFrom(from);
        setTo(to);
        setQuantity(quantity);
        this.currentOperation = LocalDateTime.now(ZoneId.of("Europe/Paris"));
    }
}
