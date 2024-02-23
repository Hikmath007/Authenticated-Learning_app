package com.hikmath.LearningPortal.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class CourseDTO {

    private String courseId;

    private String title;

    private String details;

    private Date addedDate;

    private CategoryDTO category;

    private UserDTO user;


}
