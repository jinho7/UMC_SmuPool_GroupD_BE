package com.umc.smupool.domain.map.exception.handler;

import com.umc.smupool.global.apiPayload.code.BaseErrorCode;
import com.umc.smupool.global.apiPayload.exception.GeneralException;

public class MatchingHandler extends GeneralException {
    public MatchingHandler(BaseErrorCode errorCode) {super(errorCode);}
}
