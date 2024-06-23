package com.umc.smupool.domain.chat.controller;

import com.umc.smupool.domain.chat.repository.ChatRoomRepository;
import com.umc.smupool.domain.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomService chatRoomService;

    @PostMapping("/leave/{chatRoomId}")
    public void deleteChatRoom(@RequestParam Long chatRoomId) {
        chatRoomService.deleteChatRoom(chatRoomId);
    }

}