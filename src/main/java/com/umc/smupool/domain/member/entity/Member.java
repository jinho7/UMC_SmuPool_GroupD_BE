package com.umc.smupool.domain.member.entity;


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

    public void update(String name, String nickname, String major) {
        this.name = name;
        this.nickname = nickname;
        this.major = major;
    }

    public void setMatching(Matching matching) {
        this.matching = matching;
    }

}
