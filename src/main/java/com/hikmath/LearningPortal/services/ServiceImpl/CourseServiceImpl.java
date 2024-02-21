package com.hikmath.LearningPortal.services.ServiceImpl;

import com.hikmath.LearningPortal.Dto.CourseDTO;
import com.hikmath.LearningPortal.entity.Category;
import com.hikmath.LearningPortal.entity.Course;
import com.hikmath.LearningPortal.entity.User;
import com.hikmath.LearningPortal.exceptions.ResourceNotFoundException;
import com.hikmath.LearningPortal.repository.CategoryRepository;
import com.hikmath.LearningPortal.repository.CourseRepository;
import com.hikmath.LearningPortal.repository.UserRepository;
import com.hikmath.LearningPortal.services.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Course createCourse(CourseDTO courseDTO,Long userId,Long categoryId) {
        //first fetch userbyid
        User user= this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," UserId ",userId));

        Category cat = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));


        Course course=this.modelMapper.map(courseDTO, Course.class);//we will return this below
        course.setAddedDate(new Date());
        course.setUser(user);
        course.setCategory(cat);
        Course newCourse=this.courseRepository.save(course);
        return this.modelMapper.map(newCourse,Course.class);
    }

    @Override
    public Course updateCourse(CourseDTO courseDTO, Long courseId) {
        return null;
    }

    @Override
    public void deleteCourse(Long courseId) {

    }

    @Override
    public List<Course> getAllCourse() {
        return null;
    }

    @Override
    public Course getCourseById(Long courseId) {
        return null;
    }

    @Override
    public List<Course> getCoursesByCategory(Long categoryId) {
        return null;
    }

    @Override
    public List<Course> getCoursesByUser(Long userId) {
        return null;
    }

    @Override
    public List<Course> searchCourses(String keyword) {
        return null;
    }
}
