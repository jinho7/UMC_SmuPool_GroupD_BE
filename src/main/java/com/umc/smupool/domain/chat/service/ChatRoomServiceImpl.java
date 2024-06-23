package com.umc.smupool.domain.chat.service;

import com.umc.smupool.domain.chat.entity.ChatRoom;
import com.umc.smupool.domain.chat.repository.ChatRoomRepository;
import com.umc.smupool.domain.map.entity.Matching;
import com.umc.smupool.domain.map.repository.MatchingRepository;
import com.umc.smupool.domain.member.entity.Member;
import com.umc.smupool.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.Mac;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomServiceImpl implements ChatRoomService{

    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MatchingRepository matchingRepository;

    @Override
    @Transactional
    public ChatRoom createChatRoom(List<Member> participants) {

        // ChatRoom 생성
        ChatRoom chatRoom = ChatRoom.builder()
                .build();

        // 연관관계 설정
        for (Member participant : participants) {
            chatRoom.addParticipant(participant);
        }

        // ChatRoom 저장
        chatRoomRepository.save(chatRoom);

        return chatRoom;
    }

    @Override
    @Transactional
    public List<Member> findMatchingMember(List<Long> ids) {
        return memberRepository.findAllById(ids);
    }

    @Override
    @Transactional
    public void deleteChatRoom(Long chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new EntityNotFoundException("Chat room not found"));
        Matching matching = chatRoom.getParticipants().get(0).getMatching();
        // 참가자들의 chatRoom 참조를 null로 설정
        for (Member participant : chatRoom.getParticipants()) {
            participant.setChatRoom(null);
            participant.setMatching(null);

        }
        // 채팅방 삭제
        chatRoomRepository.delete(chatRoom);
        matchingRepository.delete(matching);
    }

}
