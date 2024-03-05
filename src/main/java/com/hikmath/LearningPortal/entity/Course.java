package com.hikmath.LearningPortal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


@Entity
@Table(name = "courses")
@Data
@Getter
@Setter
public class Course {
    @Id
    @GenericGenerator(name = "course_id", strategy = "com.hikmath.LearningPortal.entity.customCourseIdGenerator")
    @GeneratedValue(generator = "course_id")

    @Column(name = "course_id")
    private String courseId;

    @Column(name = "course_title")
    private String title;

    private String details;

    private Date addedDate;

    @ManyToOne (cascade = CascadeType.ALL)
    private Category category;

    @ManyToOne
    private User user;


}
