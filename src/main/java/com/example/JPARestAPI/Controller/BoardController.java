package com.example.JPARestAPI.Controller;

import com.example.JPARestAPI.Entity.Board;
import com.example.JPARestAPI.Service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("")
    public List<Board> boardList(){
        return boardService.boardList();
    }
}
