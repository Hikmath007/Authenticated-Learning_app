package com.hikmath.LearningPortal.Dto;

import com.hikmath.LearningPortal.entity.Category;
import com.hikmath.LearningPortal.entity.User;
import jakarta.persistence.ManyToOne;

import java.util.Date;

public class CourseDTO {
    private String title;

    private String details;

    private Date addedDate;

    private Category category;
    
    private User user;

}
