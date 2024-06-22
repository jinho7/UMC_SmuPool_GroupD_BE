package com.umc.smupool.domain.map.dto.response;

import com.umc.smupool.domain.map.entity.enums.Status;
import com.umc.smupool.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class MatchingResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMatchingResultDTO{
        Long matchingId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MatchingPreviewDTO{
        Long matchingId;
        Status status;
        LocalDateTime time;
        int goal_num;
        Long carpoolZoneId;
        List<Long> memberMatchingList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MatchingPreviewListDTO {
        List<MatchingPreviewDTO> matchingPreviewDTOList;
    }

}
