package com.ironhack.bankApi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class AccountHolder extends User {
    private LocalDate dateOfBirth;
    @Embedded
    private Address primaryAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "mailing_street")),
            @AttributeOverride(name = "number", column = @Column(name = "mailing_number")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "mailing_postal_code")),
            @AttributeOverride(name = "city", column = @Column(name = "mailing_city"))
    })
    private Address mailingAddress;
    @OneToMany(mappedBy = "primaryOwner")
    @JsonIgnore
    private List<Account> mainAccountList;
    @OneToMany(mappedBy = "secondaryOwner")
    @JsonIgnore
    private List<Account> secondaryAccountList;

    public AccountHolder(String username, String password, String name, LocalDate dateOfBirth, Address primaryAddress) {
        super(username, password, name);
        setDateOfBirth(dateOfBirth);
        setPrimaryAddress(primaryAddress);
    }

    public AccountHolder(String username, String password, String name, LocalDate dateOfBirth, Address primaryAddress, Address mailingAddress) {
        super(username, password, name);
        setDateOfBirth(dateOfBirth);
        setPrimaryAddress(primaryAddress);
        setMailingAddress(mailingAddress);
    }

}
