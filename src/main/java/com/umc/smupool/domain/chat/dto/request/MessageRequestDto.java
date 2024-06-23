package com.umc.smupool.domain.chat.dto.request;

import lombok.Builder;

@Builder
public record MessageRequestDto(
        Long memberId,
        Long senderStudentId,
        String content
) { }
