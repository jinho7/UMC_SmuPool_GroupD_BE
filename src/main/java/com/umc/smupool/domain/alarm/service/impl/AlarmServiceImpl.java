package com.umc.smupool.domain.alarm.service.impl;

import com.umc.smupool.domain.alarm.dto.MessageAlarmDTO;
import com.umc.smupool.domain.alarm.service.AlarmService;
import lombok.RequiredArgsConstructor;
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
}
