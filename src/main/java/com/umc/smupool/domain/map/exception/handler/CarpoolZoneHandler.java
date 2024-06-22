package com.umc.smupool.domain.map.exception.handler;

import com.umc.smupool.global.apiPayload.code.BaseErrorCode;
import com.umc.smupool.global.apiPayload.exception.GeneralException;

public class CarpoolZoneHandler extends GeneralException {
    public CarpoolZoneHandler(BaseErrorCode errorCode){super(errorCode);}
}
