package com.umc.smupool.global.Security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umc.smupool.domain.member.exception.AuthErrorCode;
import com.umc.smupool.domain.member.exception.handler.AuthHandler;
import com.umc.smupool.global.apiPayload.ApiResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (AuthHandler e) {
            response.setContentType("application/json; charset=UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            AuthErrorCode code = (AuthErrorCode) e.getErrorCode();

            ApiResponse<Object> errorResponse = ApiResponse.onFailure(code.getCode(), code.getMessage(), null);

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), errorResponse);
        }
    }
}
