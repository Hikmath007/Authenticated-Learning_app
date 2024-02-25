package com.hikmath.LearningPortal.services;

import com.hikmath.LearningPortal.Dto.CourseDTO;
import com.hikmath.LearningPortal.entity.Category;
import com.hikmath.LearningPortal.entity.Course;
import com.hikmath.LearningPortal.entity.User;
import com.hikmath.LearningPortal.exceptions.ResourceNotFoundException;
import com.hikmath.LearningPortal.mapper.CourseMapper;
import com.hikmath.LearningPortal.repository.CategoryRepository;
import com.hikmath.LearningPortal.repository.CourseRepository;
import com.hikmath.LearningPortal.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseMapper courseMapper;

    public CourseDTO createCourse(CourseDTO courseDTO, String userId, String categoryId) {
        log.info("Creating a new course...");
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " UserId ", userId));

        log.info("User found: {}", userId);

        categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));


        Course course = courseMapper.toEntity(courseDTO);//we will return this below
        log.info("Converted Course: {}", course);

        Course newCourse = courseRepository.save(course);
        log.info("Saving the new course to the database...");
        return courseMapper.toDto(newCourse);
    }


    public CourseDTO updateCourse(CourseDTO courseDTO, String courseId) {
        courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("CourseMappe", "courseid", courseId));
        Course courses = courseMapper.toEntity(courseDTO);
        Course updatedCourse = courseRepository.save(courses);
        return courseMapper.toDto(updatedCourse);

    }

    public void deleteCourse(String courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("CourseMapper", "courseid", courseId));
        courseRepository.delete(course);
    }


    public List<CourseDTO> getAllCourse() {
        List<Course> allCourses = courseRepository.findAll();
        return allCourses.stream().map(course -> courseMapper.toDto(course)).toList();
    }


    public CourseDTO getCourseById(String courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("CourseMapper", "courseId", courseId));
        return courseMapper.toDto(course);
    }


    public List<CourseDTO> getCoursesByCategory(String categoryId) {
        //fetching the courses by id first from database and then listing below
        Category cat = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
        List<Course> courses = courseRepository.findByCategory(cat);

        return courses.stream().map(course -> courseMapper.toDto(course)).toList();
    }


    public List<CourseDTO> getCoursesByUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
        List<Course> courses = courseRepository.findByUser(user);
        return courses.stream().map(course -> courseMapper.toDto(course)).toList();
    }


}
