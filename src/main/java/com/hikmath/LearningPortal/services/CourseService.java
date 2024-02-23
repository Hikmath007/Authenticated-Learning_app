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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        //first fetch userbyid
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " UserId ", userId));

        Category cat = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));


        Course course = courseMapper.toEntity(courseDTO);//we will return this below
        Course newCourse = courseRepository.save(course);
        return courseMapper.toDto(newCourse);
    }


    public CourseDTO updateCourse(CourseDTO courseDTO, String courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("CourseMapper", "courseid", courseId));
        Course courses=courseMapper.toEntity(courseDTO);
        Course updatedCourse = courseRepository.save(courses);
        return courseMapper.toDto(updatedCourse);

    }

    public void deleteCourse(String courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("CourseMapper", "courseid", courseId));
        courseRepository.delete(course);
    }


    public List<CourseDTO> getAllCourse() {
        List<Course> allCourses = courseRepository.findAll();
        return allCourses.stream().map(course -> courseMapper.toDto(course)).collect(Collectors.toList());
    }


    public CourseDTO getCourseById(String courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("CourseMapper", "courseId", courseId));
        return courseMapper.toDto(course);
    }


    public List<CourseDTO> getCoursesByCategory(String categoryId) {
        //fetching the courses by id first from database and then listing below
        Category cat = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
        List<Course> courses = courseRepository.findByCategory(cat);

        // let's change the data from course to coursed to by using modelmapper

        List<CourseDTO> courseDTOS = courses.stream().map((course) -> courseMapper.toDto(course)).collect(Collectors.toList());
        return courseDTOS;
    }


    public List<CourseDTO> getCoursesByUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
        List<Course> courses = courseRepository.findByUser(user);
        List<CourseDTO> courseDTOS = courses.stream().map(course -> courseMapper.toDto(course)).collect(Collectors.toList());
        return courseDTOS;
    }


    public List<Course> searchCourses(String keyword) {
        return null;
    }
}
