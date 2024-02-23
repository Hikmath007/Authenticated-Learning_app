package com.hikmath.LearningPortal.mapper;

import com.hikmath.LearningPortal.Dto.CategoryDTO;
import com.hikmath.LearningPortal.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {
}
