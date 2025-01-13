package com.kevin.msvc_cursos.exception;

import feign.FeignException;

public class CustomFeignRequestException extends FeignException {
    public CustomFeignRequestException(int status, String message) {
        super(status, message);
    }
}
