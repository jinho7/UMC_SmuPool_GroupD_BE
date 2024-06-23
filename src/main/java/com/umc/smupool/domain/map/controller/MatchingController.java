package com.umc.smupool.domain.map.controller;

import com.umc.smupool.domain.map.converter.MatchingConverter;
import com.umc.smupool.domain.map.dto.request.MatchingRequestDTO;
import com.umc.smupool.domain.map.dto.response.MatchingResponseDTO;
import com.umc.smupool.domain.map.entity.Matching;
import com.umc.smupool.domain.map.service.commandService.MatchingCommandService;
import com.umc.smupool.domain.map.service.queryService.MatchingQueryService;
import com.umc.smupool.domain.member.entity.Member;
import com.umc.smupool.global.annotation.AuthMember;
import com.umc.smupool.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/matchings")
public class MatchingController {
    private final MatchingCommandService matchingCommandService;
    private final MatchingQueryService matchingQueryService;

//    @PostMapping("/")
//    public ApiResponse<MatchingResponseDTO.CreateMatchingResultDTO> createMatching(@RequestBody @Valid MatchingRequestDTO.CreateMatchingDTO request, @AuthMember Member member) {
//        Matching matching = matchingCommandService.createMatching(request, member);
//        return ApiResponse.onSuccess(MatchingConverter.toCreateMatchingResultDTO(matching));
//    }
//
//    @PostMapping("/request")
//    public void requestMatching(@RequestBody MatchingRequestDTO.CreateMatchingDTO requestDTO) {
//        matchingCommandService.addToQueue(requestDTO);
//    }

    @GetMapping("/{matchingId}")
    public ApiResponse<MatchingResponseDTO.MatchingPreviewDTO> readMatching(@PathVariable Long matchingId){
        Matching matching = matchingQueryService.readMatching(matchingId);
        return ApiResponse.onSuccess(MatchingConverter.toMatchingPreviewDTO(matching));
    }

//    @GetMapping("/")
//    public ApiResponse<MatchingResponseDTO.MatchingPreviewListDTO> readMatchings() {
//        List<Matching> matchingList = matchingQueryService.readMatchings();
//        return ApiResponse.onSuccess(MatchingConverter.toMatchingPreviewListDTO(matchingList));
//    }

//    @DeleteMapping("/{matchingId}")
//    public ApiResponse<String> deleteMatching(@PathVariable Long matchingId) {
//        matchingCommandService.deleteMatching(matchingId);
//        return ApiResponse.onSuccess("매칭이 삭제되었습니다.");
//    }
//
//    @PatchMapping("/{matchingId}/status")
//    public ApiResponse<MatchingResponseDTO.MatchingPreviewDTO> updateMatchingStatus(@PathVariable Long matchingId, @RequestBody @Valid MatchingRequestDTO.UpdateMatchingStatusDTO request) {
//        Matching matching = matchingCommandService.updateMatchingStatus(matchingId, request);
//        return ApiResponse.onSuccess(MatchingConverter.toMatchingPreviewDTO(matching));
//    }

//    @PatchMapping("/{matchingId}/goal-num")
//    public ApiResponse<MatchingResponseDTO.MatchingPreviewDTO> updateMatchingGoalNum(@PathVariable Long matchingId, @RequestBody @Valid MatchingRequestDTO.UpdateMatchingtGoalNumDTO request) {
//        Matching matching = matchingCommandService.updateMatchingGoalNum(matchingId, request);
//        return ApiResponse.onSuccess(MatchingConverter.toMatchingPreviewDTO(matching));
//    }
//
//    @PatchMapping("/{matchingId}/memberMatchingList")
//    public ApiResponse<MatchingResponseDTO.MatchingPreviewDTO> updateMatchingMemberMatching(@PathVariable Long matchingId, @AuthMember Member member) {
//        Matching matching = matchingCommandService.addMemberMatchingList(matchingId, member);
//        return ApiResponse.onSuccess(MatchingConverter.toMatchingPreviewDTO(matching));
//    }

}
