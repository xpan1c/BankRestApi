package com.ironhack.bankApi.models.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.bankApi.models.accounts.Account;
import com.ironhack.bankApi.models.utils.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
@Getter
@Setter
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

    /**
     * Add HOLDER role on creation
     */
    public AccountHolder() {
        super.getRoles().add(new Role("HOLDER",this));
    }

    /**
     * Add HOLDER role on creation
     */
    public AccountHolder(String username, String password, String name) {
        super(username, password, name);
        super.getRoles().add(new Role("HOLDER",this));
    }
    /**
     * Add HOLDER role on creation
     */
    public AccountHolder(String username, String password, String name, LocalDate dateOfBirth, Address primaryAddress) {
        super(username, password, name);
        setDateOfBirth(dateOfBirth);
        setPrimaryAddress(primaryAddress);
        super.getRoles().add(new Role("HOLDER",this));
    }
    /**
     * Add HOLDER role on creation
     */
    public AccountHolder(String username, String password, String name, LocalDate dateOfBirth, Address primaryAddress, Address mailingAddress) {
        super(username, password, name);
        setDateOfBirth(dateOfBirth);
        setPrimaryAddress(primaryAddress);
        setMailingAddress(mailingAddress);
        super.getRoles().add(new Role("HOLDER",this));
    }
}
