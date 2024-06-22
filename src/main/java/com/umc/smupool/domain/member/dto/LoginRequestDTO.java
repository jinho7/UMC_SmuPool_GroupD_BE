package com.umc.smupool.domain.member.dto;

import lombok.Getter;

@Getter
public class LoginRequestDTO {
    private Long studentId;
    private String password;
}
