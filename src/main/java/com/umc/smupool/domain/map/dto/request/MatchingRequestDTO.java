package com.umc.smupool.domain.map.dto.request;

import com.umc.smupool.domain.map.entity.enums.Status;
import com.umc.smupool.global.annotation.AuthMember;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


public class MatchingRequestDTO {

    @Getter
    public static class CreateMatchingDTO{
        Long memberId;
        int goal_num;
        Long carpoolZoneId;
        LocalDateTime time;
    }

    @Getter
    public static class UpdateMatchingStatusDTO{
        private Status status;
    }

    @Getter
    public static class UpdateMatchingtGoalNumDTO{
        private int goal_num;
    }
}
