package com.umc.smupool.domain.map.entity;

import com.umc.smupool.domain.map.entity.enums.Status;
import com.umc.smupool.domain.member.entity.Member;
import com.umc.smupool.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Matching extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="matching_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'PENDING'")
    private Status status;

    private LocalDateTime time;

    private int goal_num;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carpool_zone_id")
    private CarpoolZone carpoolZone;

    @OneToMany(mappedBy = "matching", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Member> memberMatchingList = new ArrayList<>();

    public void addMemberMatchingList(Member member) {
        memberMatchingList.add(member);
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

    public void updateGoalNum(int goal_num) {
        this.goal_num = goal_num;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static Matching create(CarpoolZone carpoolZone, int goalNum) {
        Matching matching = new Matching();
        matching.carpoolZone = carpoolZone;
        matching.goal_num = goalNum;
        matching.status = Status.MATCHED;
        matching.time = LocalDateTime.now();
        return matching;
    }


}