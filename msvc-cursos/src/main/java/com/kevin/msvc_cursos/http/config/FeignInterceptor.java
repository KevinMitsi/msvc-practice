package com.kevin.msvc_cursos.http.config;


import feign.RequestInterceptor;
import feign.RequestTemplate;


public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Content-Type", "application/json");
    }
}
