package com.umc.smupool.global.config;

import com.umc.smupool.global.Security.filter.JWTFilter;
import com.umc.smupool.global.Security.userdetails.PrincipalDetailsService;
import com.umc.smupool.global.Security.exception.JWTExceptionFilter;
import com.umc.smupool.global.Security.exception.JwtAccessDeniedHandler;
import com.umc.smupool.global.Security.exception.JwtAuthenticationEntryPoint;
import com.umc.smupool.global.Security.filter.LoginFilter;
import com.umc.smupool.global.Security.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;
    private final PrincipalDetailsService principalDetailsService;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final String[] allowUrl = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/login",
            "/auth",
            "/ws"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers(HttpMethod.POST,"/members/").permitAll()
                .requestMatchers(HttpMethod.GET,"/members/{memberId}").hasRole("USER")
                .requestMatchers(HttpMethod.GET,"/members/").hasRole("USER")
                .requestMatchers(allowUrl).permitAll()
                .anyRequest().authenticated());

        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration),jwtUtil), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(new JWTFilter(jwtUtil, principalDetailsService), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(new JWTExceptionFilter(), JWTFilter.class);

        http.exceptionHandling(
                (configurer ->
                        configurer
                                .accessDeniedHandler(jwtAccessDeniedHandler)
                                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                )
        );

        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);

        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
