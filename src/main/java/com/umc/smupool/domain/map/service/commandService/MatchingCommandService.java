package com.umc.smupool.domain.map.service.commandService;

import com.umc.smupool.domain.map.dto.request.MatchingRequestDTO;
import com.umc.smupool.domain.map.entity.Matching;

public interface MatchingCommandService {
    Matching createMatching(MatchingRequestDTO.CreateMatchingDTO request);
    void deleteMatching(Long matchingId);
    Matching updateMatchingStatus(Long matchingId, MatchingRequestDTO.UpdateMatchingStatusDTO request);
    Matching updateMatchingGoalNum(Long matchingId, MatchingRequestDTO.UpdateMatchingtGoalNumDTO request);
    Matching addMemberMatchingList(Long matchingId, MatchingRequestDTO.AddMatchingMemberMatchingListDTO request);
}
