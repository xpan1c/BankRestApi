package com.ironhack.bankApi.models.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@NoArgsConstructor
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @Setter
    @Column(nullable = false)
    private String password;
    @Setter
    private String name;
    @Setter
    @OneToMany(targetEntity= Role.class,cascade = CascadeType.ALL , fetch = FetchType.EAGER, mappedBy = "roleUser")
    @JsonIgnore
    private  List<Role> roles = new ArrayList<>();

    public User(String username, String password, String name) {
        setUsername(username);
        setPassword(password);
        setName(name);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
