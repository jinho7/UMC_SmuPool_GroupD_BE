package com.umc.smupool.domain.chat.exception;

import com.umc.smupool.global.apiPayload.ApiResponse;
import com.umc.smupool.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ChatErrorCode implements BaseErrorCode {

    // Chat ERROR 응답
    CHAT_ERROR_CODE(HttpStatus.BAD_REQUEST,
            "CHAT400",
            "채팅좀 제대로 쳐"),
    // 이렇게 동일 500을 여러 개 쓰고 싶으면 500_n 으로
    SOMETHING_WRONG_CODE(HttpStatus.INTERNAL_SERVER_ERROR,
            "CHAT500_1",
            "무슨 일이 일어났어..."),
    SOMETHING_WRONG_CODE_2(HttpStatus.INTERNAL_SERVER_ERROR,
            "CHAT500_2",
            "무슨 일이 일어났어...");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ApiResponse<Void> getErrorResponse() {
        return ApiResponse.onFailure(code, message);
    }
}
