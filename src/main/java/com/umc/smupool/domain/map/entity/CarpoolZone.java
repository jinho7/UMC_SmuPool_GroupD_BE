package com.umc.smupool.domain.map.entity;

import com.umc.smupool.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CarpoolZone extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="carpool_zone_id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "carpoolZone", cascade = CascadeType.ALL)
    private List<Matching> matchingList = new ArrayList<>();
}
