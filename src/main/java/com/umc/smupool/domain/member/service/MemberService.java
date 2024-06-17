package com.umc.smupool.domain.member.service;

import com.umc.smupool.domain.member.dto.MemberRequestDTO;
import com.umc.smupool.domain.member.entity.Member;

public interface MemberService {
    Member join(MemberRequestDTO.JoinDTO joinDTO);

    Member readMember(Long memberId);

    void deleteMember(Long memberId);

    Member updateMember(MemberRequestDTO.UpdateMemberDTO updateMemberDTO, Long memberId);

}
