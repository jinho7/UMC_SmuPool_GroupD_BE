package com.umc.smupool.domain.map.service.commandService.Impl;

import com.umc.smupool.domain.map.converter.MatchingConverter;
import com.umc.smupool.domain.map.dto.request.MatchingRequestDTO;
import com.umc.smupool.domain.map.entity.CarpoolZone;
import com.umc.smupool.domain.map.entity.Matching;
import com.umc.smupool.domain.map.event.MatchingCompletedEvent;
import com.umc.smupool.domain.map.entity.enums.Status;
import com.umc.smupool.domain.map.exception.CarpoolZoneErrorCode;
import com.umc.smupool.domain.map.exception.MatchingErrorCode;
import com.umc.smupool.domain.map.exception.handler.CarpoolZoneHandler;
import com.umc.smupool.domain.map.exception.handler.MatchingHandler;
import com.umc.smupool.domain.map.repository.CarpoolZoneRepository;
import com.umc.smupool.domain.map.repository.MatchingRepository;
import com.umc.smupool.domain.map.service.commandService.MatchingCommandService;
import com.umc.smupool.domain.member.entity.Member;
import com.umc.smupool.domain.member.exception.handler.MemberHandler;
import com.umc.smupool.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchingCommandServiceImpl implements MatchingCommandService {

    private final MatchingRepository matchingRepository;
    private final CarpoolZoneRepository carpoolZoneRepository;
    private final MemberRepository memberRepository;
    private final SimpMessagingTemplate messagingTemplate;

    private final ConcurrentHashMap<String, List<MatchingRequestDTO.CreateMatchingDTO>> matchingQueues = new ConcurrentHashMap<>();
    private final ApplicationEventPublisher eventPublisher;

    private static final Logger logger = LoggerFactory.getLogger(MatchingCommandServiceImpl.class);


    @Override
    public Matching createMatching(MatchingRequestDTO.CreateMatchingDTO request, Member member) {
        CarpoolZone carpoolZone = carpoolZoneRepository.findById(request.getCarpoolZoneId())
                .orElseThrow(() -> new CarpoolZoneHandler(CarpoolZoneErrorCode._NOT_FOUND_CARPOOLZONE));

        Matching newMatching = MatchingConverter.toMatching(request, carpoolZone);

        Matching existingMatching = isExistMatching(newMatching);

        if(existingMatching == null){
            newMatching.addMemberMatchingList(member);
            member.setMatching(newMatching);

            return matchingRepository.save(newMatching);
        }
        else {
            existingMatching.addMemberMatchingList(member);
            member.setMatching(existingMatching);

            return matchingRepository.save(existingMatching);
        }
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

    @Override
    public void addToQueue(MatchingRequestDTO.CreateMatchingDTO requestDTO) {
        String key = generateQueueKey(requestDTO.getCarpoolZoneId(), requestDTO.getGoal_num());
        matchingQueues.putIfAbsent(key, new ArrayList<>());
        matchingQueues.get(key).add(requestDTO);
    }

    @Override
    public void checkAndMatchAllQueues() {
        for (String key : matchingQueues.keySet()) {
            checkAndMatch(key);
        }
    }

    @Override
    public String generateQueueKey(Long carPoolZoneId, int goalNum) {
        return carPoolZoneId + "_" + goalNum;
    }

    @Override
    public void checkAndMatch(String key) {
        List<MatchingRequestDTO.CreateMatchingDTO> queue = matchingQueues.get(key);
        if (queue == null || queue.isEmpty()) {
            return;  // 리스트가 비어 있으면 작업을 종료
        }
        synchronized (queue) { // 멀티스레드 환경에서 안전하게 접근하기 위해 동기화
            if (queue.size() >= queue.get(0).getGoal_num()) {
                List<Long> userIds = new ArrayList<>();
                int goalNum = queue.get(0).getGoal_num();
                Long zoneId = queue.get(0).getCarpoolZoneId();

                for (int i = 0; i < goalNum; i++) {
                    if (!queue.isEmpty()) { // 큐가 비어있는지 다시 확인
                        userIds.add(queue.remove(0).getMemberId());
                    } else {
                        break; // 큐가 비어있으면 루프 종료
                    }
                }

                createMatchingRoom(userIds, zoneId);
                eventPublisher.publishEvent(new MatchingCompletedEvent(this, userIds));

                messagingTemplate.convertAndSend("/topic/matchingCompleted", userIds);
            }
        }
    }

    @Override
    public Matching createMatchingRoom(List<Long> userIds, Long carpoolZoneId) {
        // 카풀존 정보 조회
        CarpoolZone carpoolZone = carpoolZoneRepository.findById(carpoolZoneId)
                .orElseThrow(() -> new MalformedParameterizedTypeException("Carpool zone not found with ID: " + carpoolZoneId));

        // 매칭방 생성
        Matching matchingRoom = Matching.create(carpoolZone, userIds.size());

        // 매칭방을 데이터베이스에 저장
        Matching savedMatchingRoom = matchingRepository.save(matchingRoom);

        // 사용자 추가
        for (Long memberId : userIds) {
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + memberId));
            member.setMatching(savedMatchingRoom);
            savedMatchingRoom.addMemberMatchingList(member);
        }

        // 사용자 저장
        memberRepository.saveAll(userIds.stream()
                .map(memberId -> memberRepository.findById(memberId)
                        .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + memberId)))
                .toList());

        return savedMatchingRoom;
    }

    private Matching isExistMatching(Matching matching) {
        return matchingRepository.findAll().stream()
                .filter(existingMatching -> isSameMatching(existingMatching, matching))
                .findFirst()
                .orElse(null);
    }


    private boolean isSameMatching(Matching existingMatching, Matching matching) {
        return (existingMatching.getGoal_num()==matching.getGoal_num())
                && existingMatching.getCarpoolZone().equals(matching.getCarpoolZone())
                && existingMatching.getStatus().equals(Status.PENDING)
                && existingMatching.getTime().equals(matching.getTime());
    }

}