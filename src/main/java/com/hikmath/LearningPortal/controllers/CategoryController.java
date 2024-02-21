package com.hikmath.LearningPortal.controllers;
import com.hikmath.LearningPortal.Dto.ApiResponse;
import com.hikmath.LearningPortal.Dto.CategoryDTO;
import com.hikmath.LearningPortal.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //create
    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory( @RequestBody CategoryDTO categoryDTO){
        CategoryDTO createCategoryDTO = this.categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(createCategoryDTO, HttpStatus.CREATED);
    }

    //  update
    @PutMapping("/catId")
    public ResponseEntity<CategoryDTO> updateCategory( @RequestBody CategoryDTO categoryDTO, @PathVariable Long catId )
    {
        CategoryDTO updateCategory = this.categoryService.updateCategory(categoryDTO,catId);
        return new ResponseEntity<CategoryDTO>(updateCategory, HttpStatus.OK);
    }
    //delete
    @DeleteMapping("/catId")
    public ResponseEntity<ApiResponse> deleteCategory( @PathVariable Long catId )
    {
         this.categoryService.deleteCategory(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("category successfully deleted", true), HttpStatus.OK);
    }
    //getone category

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDTO>getCategory(@PathVariable long catId) {
        return ResponseEntity.ok(this.categoryService.getCategory(catId));
    }

    //getcategories

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>>getCategories() {
        return ResponseEntity.ok(this.categoryService.getCategories());
    }
}
