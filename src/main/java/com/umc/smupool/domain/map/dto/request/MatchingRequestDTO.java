package com.umc.smupool.domain.map.dto.request;

import com.umc.smupool.domain.map.entity.enums.Status;
import lombok.Getter;


public class MatchingRequestDTO {

    @Getter
    public static class CreateMatchingDTO{
        Long memberId;
        int goal_num;
        Long carpoolZoneId;
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
