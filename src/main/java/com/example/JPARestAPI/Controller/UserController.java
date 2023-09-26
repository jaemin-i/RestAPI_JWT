package com.example.JPARestAPI.Controller;

import com.example.JPARestAPI.Entity.User;
import com.example.JPARestAPI.Service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User user, HttpServletResponse response) throws Exception {
        userService.login(user, response);
        return "로그인 성공";
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User user){
        userService.signup(user);
        return "회원가입성공";
    }
}
