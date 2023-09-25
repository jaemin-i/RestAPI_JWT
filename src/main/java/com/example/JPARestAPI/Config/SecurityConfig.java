package com.example.JPARestAPI.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends AbstractHttpConfigurer {
    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) //front서버와 back서버를 연결?
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/**").permitAll() //모든권한 퍼밋
                                //.requestMatchers("/login").hasAnyRole("ADMIN") //login 페이지에 ADMIN만 접근가능
                                .anyRequest().authenticated()
                );
        return http.build();
    }
}
