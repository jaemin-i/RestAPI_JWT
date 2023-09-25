package com.example.JPARestAPI.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "board")
@NoArgsConstructor
public class Board {
    @Id //pk값
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동증가로 설정
    private Long seq;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "contents", nullable = false)
    private String contents;
}
