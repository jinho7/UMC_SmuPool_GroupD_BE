package com.umc.smupool.domain.map.controller;

import com.umc.smupool.domain.map.dto.request.MatchingRequestDTO;
import com.umc.smupool.domain.map.service.commandService.MatchingCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
public class MatchingAlarmController {

    private final MatchingCommandService matchingCommandService;

    @MessageMapping("/matching.queue")
    @SendTo("/topic/matching")
    public MatchingRequestDTO.CreateMatchingDTO addToQueue(MatchingRequestDTO.CreateMatchingDTO requestDTO) {
        matchingCommandService.addToQueue(requestDTO);
        return requestDTO;
    }

    @MessageMapping("/matching.check")
    @SendTo("/topic/matching")
    public void checkAndMatchAllQueues() {
        matchingCommandService.checkAndMatchAllQueues();
    }
}
