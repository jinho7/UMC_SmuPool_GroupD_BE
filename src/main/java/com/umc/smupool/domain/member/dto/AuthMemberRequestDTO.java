package com.umc.smupool.domain.member.dto;

import com.umc.smupool.domain.auth.dto.AuthResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthMemberRequestDTO {
    private MemberRequestDTO.JoinDTO joinDTO;
    private AuthResponseDTO authResponseDTO;
}
