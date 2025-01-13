package com.kevin.msvc_cursos.exception;

public class CourseAlreadyExistException extends RuntimeException {
    public CourseAlreadyExistException(String message) {
        super(message);
    }
}
