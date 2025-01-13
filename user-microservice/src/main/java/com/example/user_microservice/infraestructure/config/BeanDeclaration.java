package com.example.user_microservice.infraestructure.config;

import com.example.user_microservice.domain.http.FeignConnectionPort;
import com.example.user_microservice.domain.persistence.IUserPersistencePort;
import com.example.user_microservice.domain.service.IUserServicePort;
import com.example.user_microservice.domain.usecase.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanDeclaration {

    @Bean
    public IUserServicePort userServicePort(IUserPersistencePort iUserPersistencePort, FeignConnectionPort feignConnectionPort) {
        return new UserUseCase(iUserPersistencePort, feignConnectionPort);
    }
}
