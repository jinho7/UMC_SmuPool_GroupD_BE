package com.umc.smupool.domain.member.entity;


import com.umc.smupool.domain.chat.entity.ChatRoom;
import com.umc.smupool.domain.map.entity.Matching;
import com.umc.smupool.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long studentId;

    @Column(length = 255)
    private String password;

    @Column(length = 20)
    private String name;

    @Column(length = 20)
    private String nickname;

    @Column(length = 20)
    private String major;
  
    @ManyToOne
    @JoinColumn(name = "matching_id")
    private Matching matching;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    public void update(String nickname) {
        this.nickname = nickname;
    }

    public void setMatching(Matching matching) {
        this.matching = matching;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

}
