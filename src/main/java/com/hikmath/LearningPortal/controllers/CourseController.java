package com.hikmath.LearningPortal.controllers;

import com.hikmath.LearningPortal.Dto.ApiResponse;
import com.hikmath.LearningPortal.Dto.CourseDTO;
import com.hikmath.LearningPortal.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CourseController {

    @Autowired
    private CourseService courseService; //TO CREATE COURSE WE NEED SERVICE


    @PostMapping("/users/{user_id}/categories/{cat_id}/courses")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO, @PathVariable String user_id, @PathVariable String cat_id) {
        CourseDTO createCourse = courseService.createCourse(courseDTO, user_id, cat_id);
        return new ResponseEntity<CourseDTO>(createCourse, HttpStatus.CREATED);
    }

    //getCourses byUser
    @GetMapping("/users/{userId}/courses")
    public ResponseEntity<List<CourseDTO>> getCoursesByUser(@PathVariable String userId) {

        List<CourseDTO> courses = courseService.getCoursesByUser(userId);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }


    @GetMapping("/categories/{catId}/courses")
    public ResponseEntity<List<CourseDTO>> getCoursesByCategory(@PathVariable String catId) {

        List<CourseDTO> courses = courseService.getCoursesByCategory(catId);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDTO>> getAllCourse() {

        List<CourseDTO> allcourse = courseService.getAllCourse();
        return new ResponseEntity<>(allcourse, HttpStatus.OK);
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable String courseId) {
        CourseDTO courseDTO = courseService.getCourseById(courseId);
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/courses/{courseId}")
    public ApiResponse deleteCourse(@PathVariable String courseId) {
        courseService.deleteCourse(courseId);

        return new ApiResponse("category successfully deleted", true);

    }

    @PutMapping("courses/{courseId}")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable String courseId) {
        CourseDTO updatedCourse = courseService.updateCourse(courseDTO, courseId);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }
    @GetMapping("/categoryCount")
    public int getCategoryCount() {
        return courseService.getTotalCategories();
    }
}



