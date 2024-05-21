package com.umc.smupool.global.apiPayload.code.status;

import com.umc.smupool.global.apiPayload.ApiResponse;
import org.springframework.http.HttpStatus;

public interface BaseErrorCode {

    HttpStatus getHttpStatus();

    String getCode();

    String getMessage();

    ApiResponse<Void> getErrorResponse();
}
