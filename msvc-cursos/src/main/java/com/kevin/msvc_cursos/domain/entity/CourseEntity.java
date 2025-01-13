package com.kevin.msvc_cursos.domain.entity;

import com.kevin.msvc_cursos.domain.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "courses")
public class CourseEntity {
    public static final String NOT_BLANK_NAME_MESSAGE = "The name is required";
    public static final String NOT_NULL_NAME_MESSAGE = "The name is required";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = NOT_BLANK_NAME_MESSAGE)
    @NotNull(message = NOT_NULL_NAME_MESSAGE)
    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "course_id")
    private List<UserCourse>userCourses;

    @Transient
    private List<User> users;

    public CourseEntity() {
        userCourses= new ArrayList<>();
        users = new ArrayList<>();
    }

    public CourseEntity(Long id, String name) {
        this.id = id;
        this.name = name;
        userCourses= new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addUserCourse(UserCourse userCourse){
        userCourses.add(userCourse);
    }
    public void createUserCourse(User user){
        UserCourse userCourse = new UserCourse();
        userCourse.setUserId(user.getId());
        this.addUserCourse(userCourse);
    }

    public void setNameToLowerCase(){
        this.name = this.name.toLowerCase();
    }

    public void removeUserCourse(Long userId){
        UserCourse uc = new UserCourse();
        uc.setUserId(userId);
        userCourses.remove(uc);
    }



}
