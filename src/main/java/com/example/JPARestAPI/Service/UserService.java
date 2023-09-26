package com.example.JPARestAPI.Service;

import com.example.JPARestAPI.Entity.User;
import com.example.JPARestAPI.Jwt.JwtUtil;
import com.example.JPARestAPI.Repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Transactional
    public void login(User requestUser, HttpServletResponse response) throws Exception {
        String username = requestUser.getUsername();
        String password = requestUser.getPassword();

        User user = userRepository.findByUsername(username);
        if(user == null){
            System.out.println("Error");
        }

        if(!passwordEncoder.matches(password, user.getPassword())){
            System.out.println("Error");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, JwtUtil.createToken(user.getUsername(), user.getRoles()));
    }

    @Transactional
    public void signup(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRoles() == null){
            user.setRoles("ROLE_USER");
        }

        if(user.getRoles().equals("ADMIN")){
            user.setRoles("ROLE_ADMIN");
        }

        userRepository.save(user);
    }

}
