package com.umc.smupool.domain.auth.controller;

import com.umc.smupool.domain.auth.dto.AuthRequestDTO;
import com.umc.smupool.domain.auth.dto.AuthResponseDTO;
import com.umc.smupool.domain.auth.service.AuthService;
import com.umc.smupool.domain.member.converter.MemberConverter;
import com.umc.smupool.domain.member.dto.MemberResponseDTO;
import com.umc.smupool.domain.member.entity.Member;
import com.umc.smupool.global.annotation.AuthMember;
import com.umc.smupool.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ApiResponse<AuthResponseDTO> authenticate(@RequestBody @Valid AuthRequestDTO authRequestDTO) {
        return ApiResponse.onSuccess(authService.authenticate(authRequestDTO));
    }
}
