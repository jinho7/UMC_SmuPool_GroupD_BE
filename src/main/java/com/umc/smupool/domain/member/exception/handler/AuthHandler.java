package com.umc.smupool.domain.member.exception.handler;

import com.umc.smupool.global.apiPayload.code.BaseErrorCode;
import com.umc.smupool.global.apiPayload.exception.GeneralException;

public class AuthHandler extends GeneralException {
    public AuthHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
