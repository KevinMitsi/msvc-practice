package com.example.user_microservice.application.handler;

import com.example.user_microservice.infraestructure.entity.UserEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserHandler {
    Page<UserEntity> getAll();
    UserEntity save(UserEntity userEntity);
    UserEntity findById(Long id);
    void deleteById(Long id);

    List<UserEntity> getAllIn(List<Long> ids);

    void delete(Long id);
}
