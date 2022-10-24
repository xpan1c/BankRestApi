package com.ironhack.bankApi.models.users;


import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Admin extends User {
    /**
     * Add ADMIN role on creation
     */
    public Admin(String username, String password, String name) {
        super(username, password, name);
        super.getRoles().add(new Role("ADMIN",this));
    }
}
