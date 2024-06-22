package com.umc.smupool.domain.map.dto.request;

import com.umc.smupool.domain.map.entity.enums.Status;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class MatchingRequestDTO {

    @Getter
    public static class CreateMatchingDTO{
        LocalDateTime time;
        int goal_num;
        Long carpoolZoneId;
        List<Long> memberMatcingList;
    }

    @Getter
    public static class UpdateMatchingStatusDTO{
        private Status status;
    }

    @Getter
    public static class UpdateMatchingtGoalNumDTO{
        private int goal_num;
    }

    @Getter
    public static class AddMatchingMemberMatchingListDTO{
        private Long memberMatchingId;
    }
}
