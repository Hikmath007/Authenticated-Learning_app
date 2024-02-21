package com.hikmath.LearningPortal.services;

import com.hikmath.LearningPortal.Dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    //create
     CategoryDTO createCategory (CategoryDTO categoryDTO);

    //update
    CategoryDTO updateCategory (CategoryDTO categoryDTO, Long categoryId);

    //delete
      void deleteCategory (Long categoryId);

    //get
    CategoryDTO getCategory (Long categoryId);

    //get all
    List<CategoryDTO>getCategories();
}
