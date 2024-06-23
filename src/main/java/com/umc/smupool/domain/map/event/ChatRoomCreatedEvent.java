package com.umc.smupool.domain.map.event;

import org.springframework.context.ApplicationEvent;

import java.util.List;


public class ChatRoomCreatedEvent extends ApplicationEvent {
    private final Long chatRoomId;

    public ChatRoomCreatedEvent(Object source, Long chatRoomId) {
        super(source);
        this.chatRoomId = chatRoomId;
    }

    public Long getChatRoomId() {
        return chatRoomId;
    }
}
