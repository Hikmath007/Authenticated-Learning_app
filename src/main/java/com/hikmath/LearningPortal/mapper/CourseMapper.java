package com.hikmath.LearningPortal.mapper;

import com.hikmath.LearningPortal.Dto.CourseDTO;
import com.hikmath.LearningPortal.entity.Course;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CourseMapper extends EntityMapper<CourseDTO, Course>{


}
