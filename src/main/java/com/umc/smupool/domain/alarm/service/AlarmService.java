package com.umc.smupool.domain.alarm.service;

import com.umc.smupool.domain.alarm.dto.MessageAlarmDTO;

public interface AlarmService {

    void notifyChatRoom(Long chatId, MessageAlarmDTO messageAlarmDTO);
//    void onMatchingCompleted(MatchingEvent event);
//    void sendAlarm(MatchingAlarmDTO matchingAlarmDTO);
}
