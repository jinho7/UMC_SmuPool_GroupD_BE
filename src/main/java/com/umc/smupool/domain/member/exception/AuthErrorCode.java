package com.umc.smupool.domain.member.exception;

import com.umc.smupool.global.apiPayload.ApiResponse;
import com.umc.smupool.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {

    // Auth 에러
    _AUTH_EXPIRE_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH401_1", "token이 만료되었습니다."),
    _AUTH_INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH401_2", "token이 유효하지 않습니다."),
    _AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "AUTH401_3", "인증에 실패하였습니다.");

    private final HttpStatus httpStatus;

    private final String code;
    private final String message;

    @Override
    public ApiResponse<Void> getErrorResponse() {
        return ApiResponse.onFailure(code, message);
    }
}
