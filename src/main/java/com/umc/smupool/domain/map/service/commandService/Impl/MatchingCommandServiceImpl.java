package com.umc.smupool.domain.map.service.commandService.Impl;

import com.umc.smupool.domain.map.converter.MatchingConverter;
import com.umc.smupool.domain.map.dto.request.MatchingRequestDTO;
import com.umc.smupool.domain.map.entity.CarpoolZone;
import com.umc.smupool.domain.map.entity.Matching;
import com.umc.smupool.domain.map.exception.CarpoolZoneErrorCode;
import com.umc.smupool.domain.map.exception.MatchingErrorCode;
import com.umc.smupool.domain.map.exception.handler.CarpoolZoneHandler;
import com.umc.smupool.domain.map.exception.handler.MatchingHandler;
import com.umc.smupool.domain.map.repository.CarpoolZoneRepository;
import com.umc.smupool.domain.map.repository.MatchingRepository;
import com.umc.smupool.domain.map.service.commandService.MatchingCommandService;
import com.umc.smupool.domain.member.entity.Member;
import com.umc.smupool.domain.member.exception.MemberErrorCode;
import com.umc.smupool.domain.member.exception.handler.MemberHandler;
import com.umc.smupool.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchingCommandServiceImpl implements MatchingCommandService {

    private final MatchingRepository matchingRepository;
    private final MemberRepository memberRepository;
    private final CarpoolZoneRepository carpoolZoneRepository;

    @Override
    public Matching createMatching(MatchingRequestDTO.CreateMatchingDTO request, Member member) {
        CarpoolZone carpoolZone = carpoolZoneRepository.findById(request.getCarpoolZoneId())
                .orElseThrow(() -> new CarpoolZoneHandler(CarpoolZoneErrorCode._NOT_FOUND_CARPOOLZONE));

        Matching newMatching = MatchingConverter.toMatching(request, carpoolZone);

        newMatching.addMemberMatchingList(member);
        member.setMatching(newMatching);

        return matchingRepository.save(newMatching);
    }


    @Override
    public void deleteMatching(Long matchingId){
        Matching matching = matchingRepository.findById(matchingId).orElseThrow(() -> new MemberHandler(MatchingErrorCode._NOT_FOUND_MATCHING));
        matchingRepository.delete(matching);
    }

    @Override
    public Matching updateMatchingStatus(Long matchingId, MatchingRequestDTO.UpdateMatchingStatusDTO request){
        Matching matching = matchingRepository.findById(matchingId).orElseThrow(() -> new MatchingHandler(MatchingErrorCode._NOT_FOUND_MATCHING));
        matching.updateStatus(request.getStatus());
        return matching;
    }

    @Override
    public Matching updateMatchingGoalNum(Long matchingId, MatchingRequestDTO.UpdateMatchingtGoalNumDTO request){
        Matching matching = matchingRepository.findById(matchingId).orElseThrow(() -> new MatchingHandler(MatchingErrorCode._NOT_FOUND_MATCHING));
        matching.updateGoalNum(request.getGoal_num());
        return matching;
    }

    @Override
    public Matching addMemberMatchingList(Long matchingId, Member member){
        Matching matching = matchingRepository.findById(matchingId).orElseThrow(() -> new MatchingHandler(MatchingErrorCode._NOT_FOUND_MATCHING));
        matching.addMemberMatchingList(member);
        member.setMatching(matching);
        return matching;
    }

}

