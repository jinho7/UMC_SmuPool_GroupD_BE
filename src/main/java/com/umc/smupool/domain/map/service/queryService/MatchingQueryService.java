package com.umc.smupool.domain.map.service.queryService;

import com.umc.smupool.domain.map.entity.Matching;

import java.util.List;

public interface MatchingQueryService {
    Matching readMatching(Long matchingId);
    List<Matching> readMatchings();

}
