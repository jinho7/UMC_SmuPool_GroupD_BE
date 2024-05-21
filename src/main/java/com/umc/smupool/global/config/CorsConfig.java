package com.umc.smupool.global.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CorsConfig implements WebMvcConfigurer {

    public static CorsConfigurationSource apiConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        // 허용할 Origin Patterns 설정
        List<String> allowedOriginPatterns = Arrays.asList(
                "http://localhost:8000",
                "http://localhost:8080",
                "http://localhost:3000"
        );
        configuration.setAllowedOriginPatterns(allowedOriginPatterns);

        // 허용할 HTTP 메서드 설정
        List<String> allowedHttpMethods = Arrays.asList(
                "GET", "POST", "PUT", "DELETE"
        );
        configuration.setAllowedMethods(allowedHttpMethods);

        // 허용할 요청 헤더 설정
        List<String> allowedHeaders = Arrays.asList("*");
        configuration.setAllowedHeaders(allowedHeaders);

        // 쿠키 사용 여부 설정
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}