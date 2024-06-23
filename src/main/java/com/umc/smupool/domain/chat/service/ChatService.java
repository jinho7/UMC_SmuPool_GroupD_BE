package com.umc.smupool.domain.chat.service;

import com.umc.smupool.domain.chat.dto.request.MessageRequestDto;
import com.umc.smupool.domain.chat.entity.Message;
import com.umc.smupool.domain.member.entity.Member;

public interface ChatService {

    Message saveMessage(MessageRequestDto messageRequestDto);

    void sendMessage(Message message);

}
