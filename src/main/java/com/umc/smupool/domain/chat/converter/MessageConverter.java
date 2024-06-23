package com.umc.smupool.domain.chat.converter;

import com.umc.smupool.domain.chat.dto.request.MessageRequestDto;
import com.umc.smupool.domain.chat.dto.response.MessageResponseDto;
import com.umc.smupool.domain.chat.entity.Message;
import com.umc.smupool.domain.member.entity.Member;

public class MessageConverter {

    public static Message createMessageFromDto(Member sender, MessageRequestDto messageRequestDto) {
        return Message.builder()
                .sender(sender)
                .content(messageRequestDto.content())
                .chatRoom(sender.getChatRoom())
                .build();
    }

    public static MessageResponseDto toMessageResponseDto(Message message) {
        return MessageResponseDto.builder()
                .senderStudentId(message.getSender().getStudentId())
                .memberName(message.getSender().getName())
                .createdAt(message.getCreatedAt())
                .content(message.getContent())
                .build();
    }
}
