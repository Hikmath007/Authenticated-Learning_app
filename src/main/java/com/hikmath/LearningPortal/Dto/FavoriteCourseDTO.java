package com.hikmath.LearningPortal.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor

public class FavoriteCourseDTO {

    private Long favoriteId;

    private String title;

    private String details;

    private Date addedDate;

    private CategoryDTO category;

    private UserDTO user;


}
