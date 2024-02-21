package com.hikmath.LearningPortal.services.ServiceImpl;

import com.hikmath.LearningPortal.Dto.CategoryDTO;
import com.hikmath.LearningPortal.entity.Category;
import com.hikmath.LearningPortal.exceptions.ResourceNotFoundException;
import com.hikmath.LearningPortal.repository.CategoryRepository;
import com.hikmath.LearningPortal.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
          Category cat= this.modelMapper.map(categoryDTO, Category.class);
         Category addedCat = this.categoryRepository.save(cat);
        return this.modelMapper.map(addedCat, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
        Category cat = this.categoryRepository.findById(categoryId).
                orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        //once we find a category by its id , we should set title a description

        cat.setCategoryTitle(categoryDTO.getCategoryTitle());
        cat.setCategoryDescription(categoryDTO.getCategoryDescription());
        Category updatedcat= this.categoryRepository.save(cat);



        return this.modelMapper.map(updatedcat,CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category cat= this.categoryRepository.findById(categoryId).
                orElseThrow(()-> new ResourceNotFoundException("Category","category id", categoryId));
        this.categoryRepository.delete(cat);

    }

    @Override
    public CategoryDTO getCategory(Long categoryId) {
        Category cat= this.categoryRepository.findById(categoryId).
                orElseThrow(()-> new ResourceNotFoundException("Category","category id",categoryId));
       return this.modelMapper.map(cat , CategoryDTO.class);

    }

    @Override
    public List<CategoryDTO> getCategories() {
      List<Category> categories =  this.categoryRepository.findAll();
      List<CategoryDTO> categoryDTOS= categories.stream().
              map((category) ->this.modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());


        return categoryDTOS;
    }
}
