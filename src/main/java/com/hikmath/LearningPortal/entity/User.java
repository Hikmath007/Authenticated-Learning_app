package com.hikmath.LearningPortal.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GenericGenerator(name = "user_id", strategy = "com.hikmath.LearningPortal.entity.customUserIdGenerator")
    @GeneratedValue(generator = "user_id")

    @Column(name = "user_id")
    private String id;

    private String name;

    private String email;


    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Course> courses = new ArrayList<>();


}
