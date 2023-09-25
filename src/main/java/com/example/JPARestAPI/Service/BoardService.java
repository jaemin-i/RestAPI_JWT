package com.example.JPARestAPI.Service;

import com.example.JPARestAPI.Entity.Board;
import com.example.JPARestAPI.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> boardList(){
        return boardRepository.findAll();
    }
}
