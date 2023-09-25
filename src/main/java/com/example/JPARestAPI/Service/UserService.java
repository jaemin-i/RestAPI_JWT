package com.example.JPARestAPI.Service;

import com.example.JPARestAPI.DTO.UserRequestDTO;
import com.example.JPARestAPI.Entity.User;
import com.example.JPARestAPI.Entity.UserAuthEnum;
import com.example.JPARestAPI.Exception.AllException;
import com.example.JPARestAPI.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.JPARestAPI.Exception.ErrorCode.DUPLICATED_USERNAME;
import static com.example.JPARestAPI.Exception.ErrorCode.NOT_MATCH_ADMIN_TOKEN;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String ADMIN_TOKEN = "AAAbbbCCCddd159EEE258";

    public void signup(UserRequestDTO requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        //회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()){
            throw new AllException(DUPLICATED_USERNAME);
        }

        //사용자 ROLE 확인 (admin = true 일 경우 아래 코드 수행)
        UserAuthEnum role = UserAuthEnum.USER;
        if (requestDto.isAdmin()){
            if(!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new AllException(NOT_MATCH_ADMIN_TOKEN);
            }
            role = UserAuthEnum.ADMIN;
        }

        //사용자 등록 (admin = false 일 경우 아래 코드 수행)
        User user = new User(username, password, role);
        userRepository.save(user);
    }
}
