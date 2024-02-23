package com.hikmath.LearningPortal.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name = "courses")
@Data
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(generator = "course_id_generator")
    @GenericGenerator(name = "course_id_generator", strategy = "com.hikmath.LearningPortal.entity.CustomCourseIdGenerator")
    @Column(name = "course_id")
    private String courseId;


    @Column(name = "course_title")
    private String title;

    private String details;

    private Date addedDate;


    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;


}
