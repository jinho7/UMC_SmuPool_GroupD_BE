package com.umc.smupool.domain.chat.controller;

import com.umc.smupool.domain.chat.dto.request.MessageRequestDto;
import com.umc.smupool.domain.chat.entity.Message;
import com.umc.smupool.domain.chat.service.ChatService;
import com.umc.smupool.domain.member.entity.Member;
import com.umc.smupool.global.annotation.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final ChatService chatService;

    @MessageMapping("/chat/message")
    public void message(MessageRequestDto messageRequestDto) {
        Message message = chatService.saveMessage(messageRequestDto);
        chatService.sendMessage(message);
    }
}