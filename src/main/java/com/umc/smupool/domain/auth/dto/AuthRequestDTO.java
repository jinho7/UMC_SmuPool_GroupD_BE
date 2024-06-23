package com.umc.smupool.domain.auth.dto;

import com.umc.smupool.domain.member.entity.Member;
import lombok.Builder;

@Builder
public record AuthRequestDTO (
        String username, // 학번
        String password  // 샘물 비밀번호
) {}
