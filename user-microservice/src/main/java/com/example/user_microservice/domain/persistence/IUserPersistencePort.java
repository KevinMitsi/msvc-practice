package com.example.user_microservice.domain.persistence;

import com.example.user_microservice.infraestructure.entity.UserEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserPersistencePort {
    Page<UserEntity> getAll();
    UserEntity save(UserEntity userEntity);
    UserEntity findById(Long id);
    void deleteById(Long id);
    boolean existsByEmail(String email);
    List<UserEntity> getAllIn(List<Long> ids);

    void delete(Long id);
}
