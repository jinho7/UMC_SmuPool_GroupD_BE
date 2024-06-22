package com.umc.smupool.global.config.Security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umc.smupool.global.apiPayload.ApiResponse;
import com.umc.smupool.global.apiPayload.code.status.GeneralErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(403);

        ApiResponse<Object> errorResponse = ApiResponse.onFailure(
                GeneralErrorCode.FORBIDDEN_403.getCode(), GeneralErrorCode.FORBIDDEN_403.getMessage(), null);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
