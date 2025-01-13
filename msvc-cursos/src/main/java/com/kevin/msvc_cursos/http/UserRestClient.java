package com.kevin.msvc_cursos.http;

import com.kevin.msvc_cursos.http.config.FeignConfigClass;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.kevin.msvc_cursos.domain.model.User;

import java.util.List;

@FeignClient(name = "user-microservice", configuration = FeignConfigClass.class)
public interface UserRestClient {
    @GetMapping("getById/{id}")
    User getUserById(@PathVariable Long id);

    @PostMapping("save")
    User saveUser(@RequestBody User user);

    @GetMapping("getAllIn")
    List<User> getAllUsersByIds(@RequestParam List<Long> ids);
}
