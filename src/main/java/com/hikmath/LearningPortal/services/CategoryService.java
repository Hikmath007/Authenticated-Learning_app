package com.hikmath.LearningPortal.services;

import com.hikmath.LearningPortal.Dto.CategoryDTO;
import com.hikmath.LearningPortal.entity.Category;
import com.hikmath.LearningPortal.exceptions.ResourceNotFoundException;
import com.hikmath.LearningPortal.mapper.CategoryMapper;
import com.hikmath.LearningPortal.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category cat = categoryMapper.toEntity(categoryDTO);
        Category addedCat = categoryRepository.save(cat);
        return categoryMapper.toDto(addedCat);
    }


    public CategoryDTO updateCategory(CategoryDTO categoryDTO, String categoryId) {
        Category cat = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        //once we find a category by its id , we should set title a description
        Category category=categoryMapper.toEntity(categoryDTO);
        Category updatedCat = categoryRepository.save(category);

        return categoryMapper.toDto(updatedCat);
    }

    public void deleteCategory(String categoryId) {
        Category cat = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
        categoryRepository.delete(cat);

    }

    public CategoryDTO getCategory(String categoryId) {
        Category cat = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
        return categoryMapper.toDto(cat);

    }

    public List<CategoryDTO> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = categories.stream().map((category) -> categoryMapper.toDto(category)).collect(Collectors.toList());

        return categoryDTOS;
    }
}
