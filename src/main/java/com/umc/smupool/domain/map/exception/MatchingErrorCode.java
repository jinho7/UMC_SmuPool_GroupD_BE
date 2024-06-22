package com.umc.smupool.domain.map.exception;

import com.umc.smupool.global.apiPayload.ApiResponse;
import com.umc.smupool.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MatchingErrorCode implements BaseErrorCode {

    // Matching 에러
    _NOT_FOUND_MATCHING(HttpStatus.NOT_FOUND, "MATCHING400","매칭을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ApiResponse<Void> getErrorResponse() {
        return ApiResponse.onFailure(code, message);
    }
}
