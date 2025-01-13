package com.kevin.msvc_cursos.repositories;

import com.kevin.msvc_cursos.domain.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    boolean existsByName(String name);
    @Query("SELECT uc.userId FROM CourseEntity c JOIN c.userCourses uc WHERE c.id = ?1")
    List<Long> findAllUserIdsByCourseId(Long courseId);

    @Modifying
    @Query("DELETE FROM UserCourse uc WHERE uc.userId = ?1")
    void deleteAllByUserId(Long userId);
}
