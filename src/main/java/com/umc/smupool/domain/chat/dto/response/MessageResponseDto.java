package com.umc.smupool.domain.chat.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MessageResponseDto(
        Long senderStudentId,

        String memberName,

        LocalDateTime createdAt,
        String content

) { }