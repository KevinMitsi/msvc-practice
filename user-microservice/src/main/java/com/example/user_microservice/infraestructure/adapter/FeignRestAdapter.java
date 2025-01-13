package com.example.user_microservice.infraestructure.adapter;

import com.example.user_microservice.domain.http.FeignConnectionPort;
import com.example.user_microservice.infraestructure.http.CourseRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



@RequiredArgsConstructor
@Component
public class FeignRestAdapter implements FeignConnectionPort {
    private final CourseRestClient courseRestClient;
    @Override
    public void removeDeletedStudent(Long studentId) {
        courseRestClient.removeDeletedStudent(studentId);
    }
}
