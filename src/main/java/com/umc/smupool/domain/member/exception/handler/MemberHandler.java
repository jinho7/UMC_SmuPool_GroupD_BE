package com.umc.smupool.domain.member.exception.handler;


import com.umc.smupool.global.apiPayload.code.BaseErrorCode;
import com.umc.smupool.global.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode code) {
        super(code);
    }
}
