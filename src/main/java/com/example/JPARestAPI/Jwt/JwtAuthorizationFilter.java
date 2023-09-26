package com.example.JPARestAPI.Jwt;

import com.example.JPARestAPI.Auth.AuthDetail;
import com.example.JPARestAPI.Auth.AuthDetailService;
import com.example.JPARestAPI.Entity.User;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// 시큐리티 필터중 BasicAuthenticationFilter라는 것이 있음
// 권한이나 인증이 필요한 특정 주소를 요청했을 때 위 필터를 수행함

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final AuthDetailService authDetailService;

    // 인증이나 권한이 필요한 주소요청이 있을 때 해당 메소드를 실행하게 된다.
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException, ServletException, IOException {

        String jwtHeader = req.getHeader("Authorization");

        // Header 존재 유무 확인
        if(jwtHeader == null || !jwtHeader.startsWith("Bearer")){
            filterChain.doFilter(req, res);
            return ;
        }

        // JWT 토큰을 검증해서 정상적인 사용자 확인
        String jwtToken = req.getHeader("Authorization").replace("Bearer ", "");
        Claims info = jwtUtil.getUserInfoFromToken(jwtToken);

        if(!info.isEmpty()){
            AuthDetail authDetail = (AuthDetail) authDetailService.loadUserByUsername(info.getSubject());
            Authentication authentication = new UsernamePasswordAuthenticationToken(authDetail, null, authDetail.getAuthorities());

            // 시큐리티 세션에 접근하여 authentication 객체 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(req, res);
        }

    }
}
