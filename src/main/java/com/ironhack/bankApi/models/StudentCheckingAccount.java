package com.ironhack.bankApi.models;

import com.ironhack.bankApi.models.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDate;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@NoArgsConstructor
@Setter
public class StudentCheckingAccount extends Account{
    private String secretKey;
    private LocalDate creationDate;
    private Status status;
}
