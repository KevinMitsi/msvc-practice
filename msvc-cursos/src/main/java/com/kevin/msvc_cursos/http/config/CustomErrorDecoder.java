package com.kevin.msvc_cursos.http.config;

import com.kevin.msvc_cursos.exception.CustomFeignRequestException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    public static final String FEIGN_SERVER_EXCEPTION_MESSAGE = "Error dentro del servidor del microservicio foraneo: ";
    public static final String FEIGN_REQUEST_EXCEPTION_MESSAGE = "Error en la peticion que está haciendo al microservicio foraneo: ";
    private final ErrorDecoder defaultDecoder = new ErrorDecoder.Default();

    @Override
    public Exception decode(String methodKey, Response response) {

        if (response.status() >= 400 && response.status() < 500) {
            return new CustomFeignRequestException(response.status(), FEIGN_REQUEST_EXCEPTION_MESSAGE + response.reason() + response.request().url());
        } else if (response.status() >= 500) {
            return new CustomFeignRequestException(response.status(), FEIGN_SERVER_EXCEPTION_MESSAGE + response.reason() + response.request().url());
        }
        return defaultDecoder.decode(methodKey, response);
    }
}