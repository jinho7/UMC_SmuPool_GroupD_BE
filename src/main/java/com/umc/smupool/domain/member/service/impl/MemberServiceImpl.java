package com.umc.smupool.domain.member.service.impl;


import com.umc.smupool.domain.member.exception.MemberErrorCode;
import com.umc.smupool.domain.member.exception.handler.MemberHandler;
import com.umc.smupool.domain.member.converter.MemberConverter;
import com.umc.smupool.domain.member.dto.MemberRequestDTO;
import com.umc.smupool.domain.member.entity.Member;
import com.umc.smupool.domain.member.repository.MemberRepository;
import com.umc.smupool.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member join(MemberRequestDTO.JoinDTO joinDTO) {
        Member member = MemberConverter.toMember(joinDTO, passwordEncoder);
        return memberRepository.save(member);
    }

    @Override
    @Transactional(readOnly = true)
    public Member readMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() ->{
            throw new MemberHandler(MemberErrorCode._NOT_FOUND_MEMBER);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<Member> readMembers() {
        return memberRepository.findAll();
    }

    @PreAuthorize("#memberId == principal.memberId")
    @Override
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new MemberHandler(MemberErrorCode._NOT_FOUND_MEMBER));
        memberRepository.delete(member);
    }

    @Override
    public Member updateMember(Member member, MemberRequestDTO.UpdateMemberDTO updateMemberDTO) {
        member.update(updateMemberDTO.getName(), updateMemberDTO.getNickname(), updateMemberDTO.getMajor());
        return member;
    }
}
