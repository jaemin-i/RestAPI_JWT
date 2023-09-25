package com.example.JPARestAPI.Repository;

import com.example.JPARestAPI.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findAllByTitle(String title);
}
