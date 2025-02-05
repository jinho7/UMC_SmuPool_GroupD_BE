package com.umc.smupool.domain.member.controller;


import com.umc.smupool.domain.auth.dto.AuthResponseDTO;
import com.umc.smupool.domain.auth.service.AuthService;
import com.umc.smupool.domain.member.converter.MemberConverter;
import com.umc.smupool.domain.member.dto.AuthMemberRequestDTO;
import com.umc.smupool.domain.member.dto.LoginRequestDTO;
import com.umc.smupool.domain.member.dto.MemberRequestDTO;
import com.umc.smupool.domain.member.dto.MemberResponseDTO;
import com.umc.smupool.domain.member.entity.Member;
import com.umc.smupool.domain.member.service.MemberService;
import com.umc.smupool.global.annotation.AuthMember;
import com.umc.smupool.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<?> join(@RequestBody LoginRequestDTO loginRequestDTO) {
        return null;
    }

    @PostMapping("/members/")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody AuthMemberRequestDTO authMemberRequestDTO) {
        Member member = memberService.join(authMemberRequestDTO.getJoinDTO(), authMemberRequestDTO.getAuthResponseDTO());
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @GetMapping("/members/")
    public ApiResponse<MemberResponseDTO.MemberPreviewDTO> readMember(@AuthMember Member member) {
        Member readmember = memberService.readMember(member);
        return ApiResponse.onSuccess(MemberConverter.toMemberPreviewDTO(readmember));
    }

    @GetMapping("/members/all")
    public ApiResponse<MemberResponseDTO.MemberPreviewListDTO> readMembers() {
        List<Member> memberList = memberService.readMembers();
        return ApiResponse.onSuccess(MemberConverter.toMemberPreviewListDTO(memberList));
    }

    @PatchMapping("/members/")
    public ApiResponse<MemberResponseDTO.MemberPreviewDTO> updateMember(@AuthMember Member member, @RequestBody MemberRequestDTO.UpdateMemberDTO updateMemberDTO){
        Member updatemember = memberService.updateMember(member, updateMemberDTO);
        return ApiResponse.onSuccess(MemberConverter.toMemberPreviewDTO(updatemember));
    }


    @DeleteMapping("/members/")
    public ApiResponse<String> deleteMember(@AuthMember Member member) {
        memberService.deleteMember(member);
        return ApiResponse.onSuccess("멤버가 삭제되었습니다.");
    }

}
