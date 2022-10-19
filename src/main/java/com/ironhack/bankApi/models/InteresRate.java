package com.ironhack.bankApi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.math.BigDecimal;
@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class InteresRate {
    private BigDecimal interes;
}
