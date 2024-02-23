package com.hikmath.LearningPortal.repository;

import com.hikmath.LearningPortal.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {


}
