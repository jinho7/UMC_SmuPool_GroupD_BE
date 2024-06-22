package com.umc.smupool.domain.alarm.dto;

import lombok.Getter;

@Getter
public class MessageAlarmDTO {
    private Long memberId;  // 메시지 전송자
    private Long chatId;    // 채팅방
    private String content;
}