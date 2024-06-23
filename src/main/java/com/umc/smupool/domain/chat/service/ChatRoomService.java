package com.umc.smupool.domain.chat.service;

import com.umc.smupool.domain.chat.entity.ChatRoom;
import com.umc.smupool.domain.member.entity.Member;

import java.util.List;

public interface ChatRoomService {

    List<Member> findMatchingMember(List<Long> ids);
    ChatRoom createChatRoom(List<Member> participants);
    void deleteChatRoom(Long chatRoomId);

}

