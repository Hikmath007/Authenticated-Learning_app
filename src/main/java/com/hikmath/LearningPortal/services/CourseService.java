package com.hikmath.LearningPortal.services;

import com.hikmath.LearningPortal.Dto.CourseDTO;
import com.hikmath.LearningPortal.entity.Course;

import java.util.List;

public interface CourseService {

    //create
    CourseDTO createCourse(CourseDTO courseDTO, Long userId,Long categoryId);

    //Update
    CourseDTO updateCourse(CourseDTO courseDTO, Long courseId);

    //delete

    void   deleteCourse(Long courseId);

    //get all course
    List<CourseDTO>getAllCourse();

    //get coursebyID
    CourseDTO getCourseById(Long courseId);


    //getallCourseBYCategory
    List<CourseDTO>getCoursesByCategory(Long categoryId);

    //get allcourses by user
    List<CourseDTO>getCoursesByUser(Long userId);
//searchpost
    List<Course>searchCourses(String keyword);

}
