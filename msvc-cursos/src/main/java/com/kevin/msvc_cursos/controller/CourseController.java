package com.kevin.msvc_cursos.controller;

import com.kevin.msvc_cursos.domain.entity.CourseEntity;
import com.kevin.msvc_cursos.domain.model.User;
import com.kevin.msvc_cursos.services.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
@RequiredArgsConstructor
@Validated
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/all")
    public ResponseEntity<List<CourseEntity>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CourseEntity> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping("/users/{courseId}")
    public ResponseEntity<List<User>> getStudentsByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.getStudentsByCourse(courseId));
    }

    @PostMapping("/save")
    public ResponseEntity<CourseEntity> saveCourse(@Valid @RequestBody CourseEntity course) {
        return ResponseEntity.status(201).body(courseService.saveCourse(course));
    }

    @PutMapping("/update")
    public ResponseEntity<CourseEntity> updateCourse(@Valid @RequestBody CourseEntity course) {
        return ResponseEntity.ok(courseService.updateCourse(course));
    }

    @PutMapping("/addStudent/{courseId}/{studentId}")
    public ResponseEntity<CourseEntity> addStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        return ResponseEntity.ok(courseService.addUserToCourse(courseId, studentId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("removeStudent/{courseId}/{studentId}")
    public ResponseEntity<CourseEntity> removeStudentFromCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(courseService.removeUserFromCourse(courseId, studentId));
    }
    @DeleteMapping("removeDeletedStudent/{studentId}")
    public ResponseEntity<Void> removeDeletedStudentFromCourses(@PathVariable Long studentId) {
        courseService.removeDeletedStudentFromCourses(studentId);
        return ResponseEntity.noContent().build();
    }
}
