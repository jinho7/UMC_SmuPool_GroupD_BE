package com.umc.smupool.domain.map.exception;

import com.umc.smupool.global.apiPayload.ApiResponse;
import com.umc.smupool.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CarpoolZoneErrorCode implements BaseErrorCode {

    // CarpoolZone 에러
    _NOT_FOUND_CARPOOLZONE(HttpStatus.NOT_FOUND, "CARPOOLZONE400","carpoolzone을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ApiResponse<Void> getErrorResponse() {
        return ApiResponse.onFailure(code, message);
    }
}
