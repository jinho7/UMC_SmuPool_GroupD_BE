package com.umc.smupool.global.config.jwt;

import com.umc.smupool.domain.member.exception.MemberErrorCode;
import com.umc.smupool.domain.member.exception.handler.AuthHandler;
import com.umc.smupool.global.apiPayload.code.status.GeneralErrorCode;
import com.umc.smupool.global.config.PrincipalDetails;
import com.umc.smupool.global.config.PrincipalDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final PrincipalDetailsService principalDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            if (jwtUtil.isTokenValid(token)) {
                String studentId = jwtUtil.getStudentId(token);
                UserDetails userDetails = principalDetailsService.loadUserByUsername(studentId);

                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails, "", userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } else {
                    throw new AuthHandler(MemberErrorCode._NOT_FOUND_MEMBER);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
