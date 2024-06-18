package com.umc.smupool.domain.member.controller;


import com.umc.smupool.domain.member.converter.MemberConverter;
import com.umc.smupool.domain.member.dto.MemberRequestDTO;
import com.umc.smupool.domain.member.dto.MemberResponseDTO;
import com.umc.smupool.domain.member.entity.Member;
import com.umc.smupool.domain.member.service.MemberService;
import com.umc.smupool.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody MemberRequestDTO.JoinDTO joinDTO) {
        Member member = memberService.join(joinDTO);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @GetMapping("/{memberId}")
    public ApiResponse<MemberResponseDTO.MemberPreviewDTO> readMember(@PathVariable Long memberId) {
        Member member = memberService.readMember(memberId);
        return ApiResponse.onSuccess(MemberConverter.toMemberPreviewDTO(member));
    }

    @PatchMapping("/{memberId}")
    public ApiResponse<MemberResponseDTO.MemberPreviewDTO> updateMember(@RequestBody MemberRequestDTO.UpdateMemberDTO updateMemberDTO, @PathVariable Long memberId){
        Member member = memberService.updateMember(updateMemberDTO, memberId);
        return ApiResponse.onSuccess(MemberConverter.toMemberPreviewDTO(member));
    }


    @DeleteMapping("/{memberId}")
    public ApiResponse<String> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ApiResponse.onSuccess("멤버가 삭제되었습니다.");
    }


}
