package com.umc.smupool.domain.member.service;

import com.umc.smupool.domain.member.dto.MemberRequestDTO;
import com.umc.smupool.domain.member.entity.Member;

import java.util.List;

public interface MemberService {
    Member join(MemberRequestDTO.JoinDTO joinDTO);

    Member readMember(Long memberId);
    List<Member> readMembers();

    void deleteMember(Long memberId);

    Member updateMember(Member member, MemberRequestDTO.UpdateMemberDTO updateMemberDTO);
}
