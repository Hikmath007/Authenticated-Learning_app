package com.hikmath.LearningPortal.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator = "user_id_generator")
    @GenericGenerator(name = "user_id_generator", strategy = "com.hikmath.LearningPortal.entity.CustomUserIdGenerator")

    private String id;

    private String name;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courses=new ArrayList<>();

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<FavoriteCourse> favoriteCourses = new ArrayList<>();
}