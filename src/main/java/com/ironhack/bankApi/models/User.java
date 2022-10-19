package com.ironhack.bankApi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Setter
    @Column(unique = true)
    private String username;
    @Setter
    @Column(nullable = false)
    private String password;
    @Setter
    private String name;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Role> roles;

    public User(String username, String password, String name) {
        setName(username);
        setPassword(password);
        setName(name);
    }
}
