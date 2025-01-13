package com.kevin.msvc_cursos.http.config;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfigClass {
    @Bean
    ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
    @Bean
    RequestInterceptor requestInterceptor() {
        return new FeignInterceptor();
    }
}
