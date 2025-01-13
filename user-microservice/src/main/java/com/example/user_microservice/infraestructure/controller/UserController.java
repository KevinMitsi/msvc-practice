package com.example.user_microservice.infraestructure.controller;

import com.example.user_microservice.application.handler.IUserHandler;
import com.example.user_microservice.infraestructure.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/")
public class UserController {

    private final IUserHandler userHandler;
    @GetMapping("getAll")
    public Page<UserEntity> getAll() {
        return userHandler.getAll();
    }

    @GetMapping("getAllIn")
    public List<UserEntity> getAllIn(@RequestParam List<Long> ids) {
            return userHandler.getAllIn(ids);
    }

    @GetMapping("getById/{id}")
    public UserEntity getById(@PathVariable Long id) {
        return userHandler.findById(id);
    }

    @GetMapping("authorized")
    public Map<String, Object> authorized(@RequestParam(name = "code") String code) {
        //return the authorization code
        return Collections.singletonMap("authorized", code);
    }


    @PostMapping("save")
    public UserEntity save(@RequestBody UserEntity userEntity) {
        return userHandler.save(userEntity);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
            userHandler.delete(id);
        return ResponseEntity.noContent().build();
    }
}
