package com.umc.smupool.domain.member.service.impl;


import com.umc.smupool.domain.member.exception.MemberErrorCode;
import com.umc.smupool.domain.member.exception.handler.MemberHandler;
import com.umc.smupool.domain.member.converter.MemberConverter;
import com.umc.smupool.domain.member.dto.MemberRequestDTO;
import com.umc.smupool.domain.member.entity.Member;
import com.umc.smupool.domain.member.repository.MemberRepository;
import com.umc.smupool.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    @Override
    public Member join(MemberRequestDTO.JoinDTO joinDTO) {
        Member member = MemberConverter.toMember(joinDTO);
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
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new MemberHandler(MemberErrorCode._NOT_FOUND_MEMBER));
        memberRepository.delete(member);
    }

    @Override
    public Member updateMember(MemberRequestDTO.UpdateMemberDTO updateMemberDTO, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberHandler(MemberErrorCode._NOT_FOUND_MEMBER));
        member.update(updateMemberDTO.getName(), updateMemberDTO.getNickname(), updateMemberDTO.getMajor());
        return member;
    }
}
