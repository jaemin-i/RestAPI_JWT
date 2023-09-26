package com.example.JPARestAPI.Jwt;

import com.example.JPARestAPI.Auth.AuthDetail;
import com.example.JPARestAPI.Entity.User;
import com.example.JPARestAPI.Jwt.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

// 스프링 시큐리티에 로그인 관련 필터 : UsernamePasswordAuthenticationFilter
// login
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtil jwtUtil;

    //login 요청 시 알아서 실행되는 함수(수행할 코드)

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);

            //토큰으로 로그인 시도?
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    user.getUsername(), user.getPassword(), null);

            //userDetailService LoadUserByuser()
            return getAuthenticationManager().authenticate(token);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    //위 메소드가 정상적으로 수행되면 = 로그인 성공시
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String username = ((AuthDetail)authResult.getPrincipal()).getUsername();
        String role = ((AuthDetail)authResult.getPrincipal()).getUser().getRoles();

        String token = jwtUtil.createToken(username, role);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
    }
}
