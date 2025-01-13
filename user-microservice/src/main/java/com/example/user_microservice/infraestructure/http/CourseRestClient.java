package com.example.user_microservice.infraestructure.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "msvc-cursos")
public interface CourseRestClient {

    @DeleteMapping("removeDeletedStudent/{studentId}")
    void removeDeletedStudent(@PathVariable Long studentId);
}
