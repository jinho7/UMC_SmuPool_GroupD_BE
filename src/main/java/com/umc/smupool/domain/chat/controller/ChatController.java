package com.umc.smupool.domain.chat.controller;

import com.umc.smupool.global.apiPayload.ApiResponse;
import com.umc.smupool.domain.chat.exception.ChatErrorCode;
import com.umc.smupool.domain.chat.exception.ChatException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/")
public class ChatController {

    @GetMapping("/failed")
    public ApiResponse<String> ChatfailedTest() {
        if (1==1) {
            throw new ChatException(ChatErrorCode.CHAT_ERROR_CODE);
        }
        return ApiResponse.onSuccess("성공!");
    }
}
