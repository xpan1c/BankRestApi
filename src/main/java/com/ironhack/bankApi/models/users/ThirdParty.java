package com.ironhack.bankApi.models.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ThirdParty extends User{
    private String hashedKey;

    /**
     * Add THIRDPARTY role on creation
     */
    public ThirdParty(String username, String password, String name, String hashedKey) {
        super(username, password, name);
        this.hashedKey = hashedKey;
        super.getRoles().add(new Role("THIRDPARTY",this));
    }
}
