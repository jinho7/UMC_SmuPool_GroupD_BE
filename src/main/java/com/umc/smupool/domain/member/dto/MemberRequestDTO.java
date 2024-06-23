package com.umc.smupool.domain.member.dto;

import lombok.Getter;

public class MemberRequestDTO {

    @Getter
    public static class JoinDTO {
        private Long studentId;
        private String password;
    }

    @Getter
    public static class UpdateMemberDTO {
        private String nickname;
    }
}
