package com.umc.smupool.domain.alarm.controller;


import com.umc.smupool.domain.alarm.dto.MessageAlarmDTO;
import com.umc.smupool.domain.alarm.service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final AlarmService alarmService;

    @MessageMapping("/chat.send")  // /chat.send 경로로 전송된 메시지를 수신
    public void sendMessage(MessageAlarmDTO messageAlarmDTO) {
        Long chatId = messageAlarmDTO.getChatId();
        alarmService.notifyChatRoom(chatId, messageAlarmDTO);
    }
}
