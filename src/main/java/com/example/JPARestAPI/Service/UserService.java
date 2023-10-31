package com.example.JPARestAPI.Service;

import com.example.JPARestAPI.DTO.ResponseUserDTO;
import com.example.JPARestAPI.Entity.User;
import com.example.JPARestAPI.Jwt.JwtUtil;
import com.example.JPARestAPI.Repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    public List<ResponseUserDTO> info(User user) {
        List<ResponseUserDTO> responseUsers = new ArrayList<>();
        if(user.getRoles().equals("ROLE_ADMIN")){
            List<User> adminInfo = userRepository.findAll();
            for(User tempUser : adminInfo){
                responseUsers.add(new ResponseUserDTO(tempUser));
            }
            return responseUsers;
        }
        User tempUser = userRepository.findByUsername(user.getUsername());
        responseUsers.add(new ResponseUserDTO(tempUser));
        return responseUsers;
    }
}
