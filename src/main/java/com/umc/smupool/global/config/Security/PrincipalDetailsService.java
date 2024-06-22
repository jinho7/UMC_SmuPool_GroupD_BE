package com.umc.smupool.global.config.Security;

import com.umc.smupool.domain.member.entity.Member;
import com.umc.smupool.domain.member.exception.MemberErrorCode;
import com.umc.smupool.domain.member.exception.handler.MemberHandler;
import com.umc.smupool.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String studentIdString) throws UsernameNotFoundException {
        Long studentId = Long.parseLong(studentIdString);
        Member member = memberRepository.findByStudentId(studentId)
                .orElseThrow(() -> new MemberHandler(MemberErrorCode._NOT_FOUND_MEMBER));

        return new PrincipalDetails(member);
    }
}
