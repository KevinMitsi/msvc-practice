package com.kevin.msvc_cursos.services.impl;

import com.kevin.msvc_cursos.domain.entity.CourseEntity;
import com.kevin.msvc_cursos.domain.model.User;
import com.kevin.msvc_cursos.exception.CourseAlreadyExistException;
import com.kevin.msvc_cursos.http.UserRestClient;
import com.kevin.msvc_cursos.repositories.CourseRepository;
import com.kevin.msvc_cursos.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    public static final String ALREADY_EXIST_COURSE_EXCEPTION_MESSAGE = "The course already exists";
    public static final String THAT_COURSE_DOESNT_EXIST_EXCEPTION_MESSAGE = "The course does not exist";

    private final CourseRepository courseRepository;
    private final UserRestClient userRestClient;


    @Override
    @Transactional(readOnly = true)
    public List<CourseEntity> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public CourseEntity getCourseById(Long id) {
        if (!courseRepository.existsById(id)){
            throw new NoSuchElementException(THAT_COURSE_DOESNT_EXIST_EXCEPTION_MESSAGE);
        }
        return courseRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public CourseEntity saveCourse(CourseEntity course) {
        course.setNameToLowerCase();
        if (courseRepository.existsByName(course.getName())) {
            throw new CourseAlreadyExistException(ALREADY_EXIST_COURSE_EXCEPTION_MESSAGE);
        }
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public CourseEntity updateCourse(CourseEntity course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)){
            throw new NoSuchElementException(THAT_COURSE_DOESNT_EXIST_EXCEPTION_MESSAGE);
        }
        courseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CourseEntity addUserToCourse(Long courseId, Long userId) {
        CourseEntity course =courseRepository.findById(courseId).orElseThrow();

        course.createUserCourse(userRestClient.getUserById(userId));

        return updateCourse(course);
    }

    @Override
    @Transactional
    public CourseEntity removeUserFromCourse(Long courseId, Long userId) {
        CourseEntity course =courseRepository.findById(courseId).orElseThrow();
        course.removeUserCourse(userId);
        return updateCourse(course);
    }

    @Override
    @Transactional
    public List<User> getStudentsByCourse(Long courseId) {
        return userRestClient.getAllUsersByIds(courseRepository.findAllUserIdsByCourseId(courseId));
    }

    @Override
    @Transactional
    public void removeDeletedStudentFromCourses(Long studentId) {
        courseRepository.deleteAllByUserId(studentId);
    }
}
