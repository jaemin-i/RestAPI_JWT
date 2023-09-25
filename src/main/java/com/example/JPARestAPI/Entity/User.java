package com.example.JPARestAPI.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "user_auth")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "auth")
    @Enumerated(value = EnumType.STRING)
    private UserAuthEnum auth;

    public User(String username, String password, UserAuthEnum role) {
        this.username = username;
        this.password = password;
        this.auth = role;
    }
}
