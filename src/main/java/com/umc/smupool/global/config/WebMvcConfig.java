package com.umc.smupool.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
// View Controllers, Interceptors, Argument Resolvers 등 설정 가능
public class WebMvcConfig implements WebMvcConfigurer {

    public void addArgumentResolvers() {
        // TODO : ✔ Argument Resolver
        //  스프링 프레임워크에서 HTTP 요청 파라미터를 컨트롤러 메서드의 파라미터로 변환해주는 기능
    }

}