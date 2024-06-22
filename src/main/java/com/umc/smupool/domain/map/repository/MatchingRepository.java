package com.umc.smupool.domain.map.repository;

import com.umc.smupool.domain.map.entity.Matching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchingRepository extends JpaRepository<Matching, Long> {
}
