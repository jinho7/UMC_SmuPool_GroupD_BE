package com.umc.smupool.domain.alarm.service.impl;

import com.umc.smupool.domain.alarm.dto.MessageAlarmDTO;
import com.umc.smupool.domain.alarm.service.AlarmService;
import com.umc.smupool.domain.map.event.MatchingCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AlarmServiceImpl implements AlarmService {

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void notifyChatRoom(Long chatId, MessageAlarmDTO messageAlarmDTO) {
        messagingTemplate.convertAndSend("/topic/room/" + chatId, messageAlarmDTO);
    }

//    @Override
//    @EventListener
//    public void onMatchingCompleted(MatchingEvent event) {
//
//        // 매칭된 사용자애게 알림 전송
//        for (Long memberId : event.getUserIds()) {
//            MatchingAlarmDTO matchingAlarmDTO = new MatchingAlarmDTO(
//                    "매칭 완료",
//                    "당신의 카풀 매칭이 완료되었습니다!",
//                    memberId
//            );
//            sendAlarm(matchingAlarmDTO);
//        }
//    }
//
//    @Override
//    public void sendAlarm(MatchingAlarmDTO matchingAlarmDTO) {
//
//        // 사용자의 개인 알림 경로로 메시지 전송
//        String destination = "/queue/notifications/" + matchingAlarmDTO.getMemberId();
//        messagingTemplate.convertAndSend(destination, matchingAlarmDTO);
//    }

    @EventListener
    public void handleMatchingCompleted(MatchingCompletedEvent event) {
        // 매칭 완료 이벤트를 처리하고 /topic/matchingCompleted 경로로 메시지 전송
        messagingTemplate.convertAndSend("/topic/matchingCompleted", event.getUserIds());
    }
}
