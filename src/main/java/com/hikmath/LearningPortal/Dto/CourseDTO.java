package com.hikmath.LearningPortal.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;

@Getter
@Setter
@NoArgsConstructor

public class CourseDTO {

    private Long courseId;

    private String title;

    private String details;

    private Date addedDate;

    private CategoryDTO category;
    
    private UserDTO user;


}
