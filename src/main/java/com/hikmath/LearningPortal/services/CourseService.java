package com.hikmath.LearningPortal.services;

import com.hikmath.LearningPortal.Dto.CourseDTO;
import com.hikmath.LearningPortal.entity.Course;

import java.util.List;

public interface CourseService {

    //create
    Course createCourse(CourseDTO courseDTO, Long userId,Long categoryId);

    //Update
    Course updateCourse(CourseDTO courseDTO, Long courseId);

    //delete

    void   deleteCourse(Long courseId);

    //get all course
    List<Course>getAllCourse();

    //get coursebyID
    Course getCourseById(Long courseId);


    //getallCourseBYCategory
    List<Course>getCoursesByCategory(Long categoryId);

    //get allcourses by user
    List<Course>getCoursesByUser(Long userId);
//searchpost
    List<Course>searchCourses(String keyword);

}
