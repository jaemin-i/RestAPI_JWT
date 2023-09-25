package com.example.JPARestAPI.Controller;

import com.example.JPARestAPI.DTO.ResultMessageDTO;
import com.example.JPARestAPI.DTO.UserRequestDTO;
import com.example.JPARestAPI.Service.UserService;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResultMessageDTO> signup(@RequestBody UserRequestDTO userRequestDTO){
        userService.signup(userRequestDTO);
        return ResponseEntity.ok(new ResultMessageDTO("회원가입 성공!", HttpStatus.OK.value()));
    }
}
