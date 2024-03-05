package com.hikmath.LearningPortal.repository;

import com.hikmath.LearningPortal.entity.Category;
import com.hikmath.LearningPortal.entity.Course;
import com.hikmath.LearningPortal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {
    List<Course> findByUser(User user);
    List<Course>findByCategory(Category category);

    @Query(value = "SELECT COUNT(cat_id) FROM category", nativeQuery = true)
    int countCategories();
}
