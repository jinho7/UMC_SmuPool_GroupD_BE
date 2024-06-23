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
    _AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "AUTH401_3", "인증에 실패하였습니다."),

    // 샘물 로그인 에러
    AUTH_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "AUTH401", "아이디 및 비밀번호가 일치하지 않습니다."),
    AUTH_INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "AUTH500", "인증 서버 에러, 관리자에게 문의 바랍니다.");

    private final HttpStatus httpStatus;

    private final String code;
    private final String message;

    @Override
    public ApiResponse<Void> getErrorResponse() {
        return ApiResponse.onFailure(code, message);
    }
}
