package com.umc.smupool.global.annotation;

import com.umc.smupool.domain.member.entity.Member;
import com.umc.smupool.domain.member.exception.MemberErrorCode;
import com.umc.smupool.domain.member.exception.handler.MemberHandler;
import com.umc.smupool.domain.member.repository.MemberRepository;
import com.umc.smupool.global.Security.userdetails.PrincipalDetails;
import com.umc.smupool.global.apiPayload.code.status.GeneralErrorCode;
import com.umc.smupool.global.apiPayload.exception.GeneralException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class AuthMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final MemberRepository memberRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasParameterAnnotation = parameter.hasParameterAnnotation(AuthMember.class);
        boolean isUserParameterType = parameter.getParameterType().isAssignableFrom(Member.class);
        log.info("supportsParameter" + hasParameterAnnotation + isUserParameterType);
        return hasParameterAnnotation && isUserParameterType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

        Object userDetails = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Long studentId = Long.valueOf(((PrincipalDetails)userDetails).getUsername());

        return memberRepository.findByStudentId(studentId)
                .orElseThrow(() -> new MemberHandler(MemberErrorCode._NOT_FOUND_MEMBER));
    }
}