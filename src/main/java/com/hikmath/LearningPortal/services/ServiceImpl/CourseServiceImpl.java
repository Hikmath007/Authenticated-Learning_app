package com.hikmath.LearningPortal.services.ServiceImpl;

import com.hikmath.LearningPortal.Dto.CategoryDTO;
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
import java.util.stream.Collectors;

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
    public CourseDTO createCourse(CourseDTO courseDTO,Long userId,Long categoryId) {
        //first fetch userbyid
        User user= this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," UserId ",userId));

        Category cat = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));


        Course course=this.modelMapper.map(courseDTO, Course.class);//we will return this below
        course.setAddedDate(new Date());
        course.setUser(user);
        course.setCategory(cat);
        Course newCourse=this.courseRepository.save(course);
        return this.modelMapper.map(newCourse,CourseDTO.class);
    }

    @Override
    public CourseDTO updateCourse(CourseDTO courseDTO, Long courseId) {
        Course course=this.courseRepository.findById(courseId).orElseThrow(()->new ResourceNotFoundException("Course","courseid",courseId));
          course.setTitle(courseDTO.getTitle());
          course.setDetails(courseDTO.getDetails());
          Course updatedCourse=this.courseRepository.save(course);
          return this.modelMapper.map(updatedCourse,CourseDTO.class);

    }

    @Override
    public void deleteCourse(Long courseId) {
        Course course=this.courseRepository.findById(courseId).orElseThrow(()->new ResourceNotFoundException("Course","courseid",courseId));
       this.courseRepository.delete(course);
    }

    @Override
    public List<CourseDTO> getAllCourse() {
        List<Course>allcourses=this.courseRepository.findAll();
        return allcourses.stream().
                map((course) ->this.modelMapper.map(course, CourseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CourseDTO getCourseById(Long courseId) {
           Course course= this.courseRepository.findById(courseId).orElseThrow(()->new ResourceNotFoundException("Course","courseId",courseId));
           return this.modelMapper.map(course, CourseDTO.class);
    }

    @Override
    public List<CourseDTO> getCoursesByCategory(Long categoryId) {

        //fetching the courses byid first from database and then listing below
        Category cat=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id", categoryId));
        List<Course>courses=this.courseRepository.findByCategory(cat);

      //lets change the data from course to coursedto by using modelmapper

        List<CourseDTO>courseDTOS = courses.stream().map((course)->this.modelMapper.map(course, CourseDTO.class)).collect(Collectors.toList());
          return courseDTOS;
    }

    @Override
    public List<CourseDTO> getCoursesByUser(Long userId) {
        User user= this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user id",userId));
        List<Course> courses= this.courseRepository.findByUser(user);
        List<CourseDTO>courseDTOS=courses.stream().map((course) ->this.modelMapper.map(course, CourseDTO.class)).collect(Collectors.toList());

        return courseDTOS;
    }

    @Override
    public List<Course> searchCourses(String keyword) {
        return null;
    }
}
