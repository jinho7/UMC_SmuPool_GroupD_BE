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

    @OneToMany(mappedBy = "matching", cascade = CascadeType.ALL)
    private List<Member> memberList = new ArrayList<>();

}
