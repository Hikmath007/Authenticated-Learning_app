package com.hikmath.LearningPortal.services;

import com.hikmath.LearningPortal.Dto.CategoryDTO;
import com.hikmath.LearningPortal.entity.Category;
import com.hikmath.LearningPortal.exceptions.ResourceNotFoundException;
import com.hikmath.LearningPortal.mapper.CategoryMapper;
import com.hikmath.LearningPortal.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        log.info("Creating a new category...");

        Category cat = categoryMapper.toEntity(categoryDTO);
        log.info("Converted CategoryDto: {}", "category");

        log.info("Saving the new category to the database...");
        Category addedCat = categoryRepository.save(cat);
        return categoryMapper.toDto(addedCat);
    }


    public CategoryDTO updateCategory(CategoryDTO categoryDTO, String categoryId) {
        log.info("Updating category with ID: {}", categoryId);
        categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Categori", "categoryId", categoryId));
        Category category = categoryMapper.toEntity(categoryDTO);
        log.info("Updated Category: {}", category);

        Category updatedCat = categoryRepository.save(category);

        return categoryMapper.toDto(updatedCat);
    }

    public void deleteCategory(String categoryId) {
        log.info("Deleting category with ID: {}", categoryId);

        Category cat = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
        categoryRepository.delete(cat);

    }

    public CategoryDTO getCategory(String categoryId) {
        Category cat = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
        return categoryMapper.toDto(cat);

    }

    public List<CategoryDTO> getCategories() {
        log.info("Fetching all categories...");

        List<Category> categories = categoryRepository.findAll();
        log.info("Fetched {} categories", categories.size());


        return categories.stream().map(category -> categoryMapper.toDto(category)).toList();
    }
}
