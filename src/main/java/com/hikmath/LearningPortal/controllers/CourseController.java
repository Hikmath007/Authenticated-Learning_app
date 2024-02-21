package com.hikmath.LearningPortal.controllers;

import com.hikmath.LearningPortal.Dto.ApiResponse;
import com.hikmath.LearningPortal.Dto.CourseDTO;
import com.hikmath.LearningPortal.Dto.UserDTO;
import com.hikmath.LearningPortal.services.CourseService;
import jakarta.validation.Valid;
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
    //create
     @PostMapping("/users/{userId}/categories/{categoryId}/courses")
    public ResponseEntity<CourseDTO> createCourse(
      @RequestBody CourseDTO courseDTO,
      @PathVariable Long userId,
      @PathVariable Long categoryId)
     {
         CourseDTO createCourse=this.courseService.createCourse(courseDTO,userId,categoryId);
         return new ResponseEntity<CourseDTO>(createCourse, HttpStatus.CREATED);
     }

    //getCourses byUser
    @GetMapping("/users/{userId}/courses")
    public ResponseEntity<List<CourseDTO>>getCoursesByUser( @PathVariable Long userId){

         List<CourseDTO> courses= this.courseService.getCoursesByUser(userId);
            return  new ResponseEntity<List<CourseDTO>>(courses,HttpStatus.OK);
    }
// get coursesby category

    @GetMapping("/categories/{catId}/courses")
    public ResponseEntity<List<CourseDTO>>getCoursesByCategory( @PathVariable Long catId){

        List<CourseDTO> courses= this.courseService.getCoursesByCategory(catId);
        return  new ResponseEntity<List<CourseDTO>>(courses,HttpStatus.OK);
    }

    //get allcourses

    @GetMapping("/courses")
      public ResponseEntity<List<CourseDTO>> getAllCourse() {

        List<CourseDTO>allcourse=this.courseService.getAllCourse();
        return new ResponseEntity<List<CourseDTO>>(allcourse,HttpStatus.OK);
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<CourseDTO>getCourseById(@PathVariable Long courseId){
         CourseDTO courseDTO= courseService.getCourseById(courseId);
         return new ResponseEntity<CourseDTO>(courseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/courses/{courseId}")
    public ApiResponse deleteCourse(@PathVariable Long courseId){
         this.courseService.deleteCourse(courseId);

         return  new ApiResponse("course is succesfully deleted", true);
}
     @PutMapping("courses/{courseId}")
       public ResponseEntity<CourseDTO>updateCourse( @RequestBody CourseDTO courseDTO,@PathVariable Long courseId){
         CourseDTO updatedCourse=this.courseService.updateCourse( courseDTO, courseId);
         return new ResponseEntity<CourseDTO>(updatedCourse,HttpStatus.OK);
     }
}



