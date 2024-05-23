package com.umc.smupool.domain.health.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    // 배포 된 WAS가 잘 동작하는 지 확인
    @GetMapping("/health")
    public String healthCheck() {
        return "I'm healthy!";
    }
}
