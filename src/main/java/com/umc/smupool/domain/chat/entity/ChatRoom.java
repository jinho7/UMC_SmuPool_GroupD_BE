package com.umc.smupool.domain.chat.entity;

import com.umc.smupool.domain.member.entity.Member;
import com.umc.smupool.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "chat_room")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "chatRoom")
    private List<Member> participants = new ArrayList<>();


    public void addParticipant(Member member) {
        if (participants.size() < 4) {
            participants.add(member);
            member.setChatRoom(this);
        } else {
            throw new IllegalStateException("Chat room is full. Maximum 4 participants allowed.");
        }
    }

    public void removeParticipant(Member member) {
        participants.remove(member);
        member.setChatRoom(null);
    }
}