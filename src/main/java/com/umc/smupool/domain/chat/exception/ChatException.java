package com.umc.smupool.domain.chat.exception;

import com.umc.smupool.global.apiPayload.code.status.BaseErrorCode;
import com.umc.smupool.global.apiPayload.exception.GeneralException;
import lombok.Getter;

@Getter
public class ChatException extends GeneralException {
    public ChatException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
