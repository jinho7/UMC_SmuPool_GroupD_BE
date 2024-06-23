package com.umc.smupool.domain.member.converter;


import com.umc.smupool.domain.auth.dto.AuthRequestDTO;
import com.umc.smupool.domain.auth.dto.AuthResponseDTO;
import com.umc.smupool.domain.member.dto.MemberRequestDTO;
import com.umc.smupool.domain.member.dto.MemberResponseDTO;
import com.umc.smupool.domain.member.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class MemberConverter {

    public static Member toMember(MemberRequestDTO.JoinDTO joinDTO, PasswordEncoder passwordEncoder, AuthResponseDTO authResponseDTO) {
        return Member.builder()
                .studentId(joinDTO.getStudentId())
                .password(passwordEncoder.encode(joinDTO.getPassword()))
                .name(authResponseDTO.name())
                .nickname(authResponseDTO.name())
                .major(authResponseDTO.department())
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

    public static MemberResponseDTO.MemberPreviewListDTO toMemberPreviewListDTO(List<Member> memberList) {
        List<MemberResponseDTO.MemberPreviewDTO> memberPreviewDTOList = memberList.stream()
                .map(MemberConverter::toMemberPreviewDTO).toList();

        return MemberResponseDTO.MemberPreviewListDTO.builder()
                .memberPreviewDTOList(memberPreviewDTOList)
                .build();
    }
}
