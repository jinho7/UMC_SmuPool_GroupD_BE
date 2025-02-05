package com.umc.smupool.domain.map.service.queryService.Impl;

import com.umc.smupool.domain.map.converter.MatchingConverter;
import com.umc.smupool.domain.map.entity.Matching;
import com.umc.smupool.domain.map.entity.enums.Status;
import com.umc.smupool.domain.map.exception.MatchingErrorCode;
import com.umc.smupool.domain.map.exception.handler.MatchingHandler;
import com.umc.smupool.domain.map.repository.MatchingRepository;
import com.umc.smupool.domain.map.service.commandService.MatchingCommandService;
import com.umc.smupool.domain.map.service.queryService.MatchingQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchingQueryServiceImpl implements MatchingQueryService {

    private final MatchingRepository matchingRepository;

    @Override
    public Matching readMatching(Long matchingId) {
        Matching matching = matchingRepository.findById(matchingId).orElseThrow(() -> {
            throw new MatchingHandler(MatchingErrorCode._NOT_FOUND_MATCHING);
        });
        if(matching.getGoal_num() == matching.getMemberMatchingList().size()) {
            matching.setStatus(Status.MATCHED);
            matching = matchingRepository.save(matching);
        }
        return matching;
    }

    @Override
    public List<Matching> readMatchings() {
        return matchingRepository.findAll();
    }
}
