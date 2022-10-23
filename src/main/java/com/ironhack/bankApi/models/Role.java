package com.ironhack.bankApi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String role;

    @ManyToOne

    private User roleUser;

    public Role(String role, User roleUser) {
        setRole(role);
        setRoleUser(roleUser);
    }
}
