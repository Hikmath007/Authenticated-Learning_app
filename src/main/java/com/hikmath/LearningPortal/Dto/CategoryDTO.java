package com.hikmath.LearningPortal.Dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

    private Long categoryId;

    private String categoryTitle;

    private String CategoryDescription;
}
