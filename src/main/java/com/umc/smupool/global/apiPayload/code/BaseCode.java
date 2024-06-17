package com.umc.smupool.global.apiPayload.code;

import com.umc.smupool.global.apiPayload.ApiResponse;
import org.springframework.http.HttpStatus;

public interface BaseCode {
    HttpStatus getHttpStatus();

    String getCode();

    String getMessage();

    ApiResponse<Void> getErrorResponse();
}
