package com.umc.smupool.domain.map.converter;

import com.umc.smupool.domain.map.dto.request.MatchingRequestDTO;
import com.umc.smupool.domain.map.dto.response.MatchingResponseDTO;
import com.umc.smupool.domain.map.entity.CarpoolZone;
import com.umc.smupool.domain.map.entity.Matching;
import com.umc.smupool.domain.map.service.commandService.CarpoolZoneCommandService;
import com.umc.smupool.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MatchingConverter {

    public static MatchingResponseDTO.CreateMatchingResultDTO toCreateMatchingResultDTO(Matching matching) {
        return MatchingResponseDTO.CreateMatchingResultDTO.builder()
                .matchingId(matching.getId())
                .createdAt(matching.getCreatedAt())
                .build();
    }

    public static Matching toMatching(MatchingRequestDTO.CreateMatchingDTO request, CarpoolZone carpoolZone) {

        return Matching.builder()
                .time(request.getTime())
                .goal_num(request.getGoal_num())
                .carpoolZone(carpoolZone)
                .memberMatchingList(new ArrayList<>())
                .build();
    }

    public static MatchingResponseDTO.MatchingPreviewDTO toMatchingPreviewDTO(Matching matching) {
        List<Long> memberMatchingIds = matching.getMemberMatchingList().stream()
                .map(Member::getId)
                .collect(Collectors.toList());


        return MatchingResponseDTO.MatchingPreviewDTO.builder()
                .matchingId(matching.getId())
                .status(matching.getStatus())
                .time(matching.getTime())
                .goal_num(matching.getGoal_num())
                .carpoolZoneId(matching.getCarpoolZone().getId())
                .memberMatchingList(memberMatchingIds)
                .currentMatchedCount(matching.getMemberMatchingList().size())
                .build();
    }

    public static MatchingResponseDTO.MatchingPreviewListDTO toMatchingPreviewListDTO(List<Matching> matchingList) {
        List<MatchingResponseDTO.MatchingPreviewDTO> matchingPreviewDTOList = matchingList.stream()
                .map(MatchingConverter::toMatchingPreviewDTO)
                .toList();
        return MatchingResponseDTO.MatchingPreviewListDTO.builder()
                .matchingPreviewDTOList(matchingPreviewDTOList)
                .build();
    }

}
