package com.umc.smupool.domain.member.converter;


import com.umc.smupool.domain.member.dto.MemberRequestDTO;
import com.umc.smupool.domain.member.dto.MemberResponseDTO;
import com.umc.smupool.domain.member.entity.Member;

public class MemberConverter {

    public static Member toMember(MemberRequestDTO.JoinDTO joinDTO) {
        return Member.builder()
                .studentId(joinDTO.getStudentId())
                .password(joinDTO.getPassword()) // passwordEncoder
                .build();
    }

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static MemberResponseDTO.MemberPreviewDTO toMemberPreviewDTO(Member member) {
        return MemberResponseDTO.MemberPreviewDTO.builder()
                .memberId(member.getId())
                .studentId(member.getStudentId())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .build();
    }
}
