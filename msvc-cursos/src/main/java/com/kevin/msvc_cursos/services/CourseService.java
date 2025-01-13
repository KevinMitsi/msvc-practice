package com.kevin.msvc_cursos.services;

import com.kevin.msvc_cursos.domain.entity.CourseEntity;
import com.kevin.msvc_cursos.domain.model.User;

import java.util.List;

public interface CourseService {
    List<CourseEntity> getAllCourses();
     CourseEntity getCourseById(Long id);
     CourseEntity saveCourse(CourseEntity course);
     CourseEntity updateCourse(CourseEntity course);
     void deleteCourse(Long id);
     CourseEntity addUserToCourse(Long courseId, Long userId);
     CourseEntity removeUserFromCourse(Long courseId, Long userId);

    List<User> getStudentsByCourse(Long courseId);

    void removeDeletedStudentFromCourses(Long studentId);
}
