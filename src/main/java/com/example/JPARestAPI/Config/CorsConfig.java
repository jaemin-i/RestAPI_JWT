package com.example.JPARestAPI.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){ //cors : 특정 ip에서만 요청가능하게함
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*"); //모든 IP에 대한 응답
        config.addAllowedHeader("*"); //모든 헤더에 대한 응답
        config.addAllowedMethod("*"); //모든 메소드에 대한 응답
        source.registerCorsConfiguration("/api/*", config);

        return new CorsFilter(source);
    }
}

