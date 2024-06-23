package com.umc.smupool.domain.alarm.controller.scheduler;

import com.umc.smupool.domain.map.service.commandService.MatchingCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class MatchingScheduler {

    private final MatchingCommandService matchingCommandService;

    // 1초마다 매칭 큐를 체크하여 처리하는 메서드
    @Scheduled(fixedRate = 1000)  // 1초마다 실행
    public void processMatchingQueue() {
        matchingCommandService.checkAndMatchAllQueues();
    }

}