package com.umc.smupool.domain.member.service;

import com.umc.smupool.domain.auth.dto.AuthResponseDTO;
import com.umc.smupool.domain.member.dto.MemberRequestDTO;
import com.umc.smupool.domain.member.entity.Member;

import java.util.List;

public interface MemberService {
    Member join(MemberRequestDTO.JoinDTO joinDTO, AuthResponseDTO authResponseDTO);

    Member readMember(Member member);
    List<Member> readMembers();

    void deleteMember(Member member);

    Member updateMember(Member member, MemberRequestDTO.UpdateMemberDTO updateMemberDTO);
}
