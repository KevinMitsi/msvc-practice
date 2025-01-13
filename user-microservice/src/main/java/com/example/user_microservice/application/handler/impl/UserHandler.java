package com.example.user_microservice.application.handler.impl;

import com.example.user_microservice.application.handler.IUserHandler;
import com.example.user_microservice.domain.service.IUserServicePort;
import com.example.user_microservice.infraestructure.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserHandler  implements IUserHandler {
    private final IUserServicePort userServicePort;

    @Override
    public Page<UserEntity> getAll() {
        return userServicePort.getAll();
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userServicePort.save(userEntity);
    }

    @Override
    public UserEntity findById(Long id) {
        return userServicePort.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        userServicePort.deleteById(id);
    }

    @Override
    public List<UserEntity> getAllIn(List<Long> ids) {
        return userServicePort.getAllIn(ids);
    }
    @Override
    public void delete(Long id) {
        userServicePort.delete(id);
    }
}
