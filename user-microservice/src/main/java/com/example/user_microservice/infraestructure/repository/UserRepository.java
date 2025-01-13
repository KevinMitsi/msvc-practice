package com.example.user_microservice.infraestructure.repository;

import com.example.user_microservice.infraestructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    boolean existsByEmail(String email);

}
