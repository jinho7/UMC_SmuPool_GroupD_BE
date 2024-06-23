package com.umc.smupool.domain.chat.service;

import com.umc.smupool.domain.chat.converter.MessageConverter;
import com.umc.smupool.domain.chat.dto.request.MessageRequestDto;
import com.umc.smupool.domain.chat.dto.response.MessageResponseDto;
import com.umc.smupool.domain.chat.entity.Message;
import com.umc.smupool.domain.chat.repository.MessageRepository;
import com.umc.smupool.domain.member.entity.Member;
import com.umc.smupool.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatServiceImpl implements ChatService{

    private final SimpMessageSendingOperations messagingTemplate;
    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Message saveMessage(MessageRequestDto messageRequestDto) {
        Member member = memberRepository.findById(messageRequestDto.memberId())
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        Message message = MessageConverter.createMessageFromDto(member, messageRequestDto);
        messageRepository.save(message);
        return message;
    }

    @Override
    @Transactional
    public void sendMessage(Message message) {
        MessageResponseDto responseDto = MessageConverter.toMessageResponseDto(message);
        messagingTemplate.convertAndSend("/topic/chat/room/" + message.getChatRoom().getId(), responseDto);
    }

}
