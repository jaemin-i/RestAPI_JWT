package com.example.JPARestAPI.Auth;

import com.example.JPARestAPI.Entity.User;
import com.example.JPARestAPI.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findAllByUsername(username).orElseThrow(
                () -> {return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다." + username);}
        );
        return new AuthDetail(user);
    }
}
