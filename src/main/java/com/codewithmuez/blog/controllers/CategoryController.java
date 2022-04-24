package com.codewithmuez.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codewithmuez.blog.payloads.ApiResponse;
import com.codewithmuez.blog.payloads.CategoryDTO;
import com.codewithmuez.blog.services.CategoryService;
import com.codewithmuez.blog.services.UserService;

@RestController
@RequestMapping("api/category")
public class CategoryController {
	
	@Autowired
	public CategoryService categoryService;
	
	
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO ) {
		CategoryDTO createCategoryDTO = this.categoryService.createCategory(categoryDTO);
		return new ResponseEntity<>(createCategoryDTO,HttpStatus.CREATED);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer categoryId){
		return ResponseEntity.ok(this.categoryService.getCategoryById(categoryId));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getAllCategory(){	
		return ResponseEntity.ok(this.categoryService.getAllCategory());
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO>  updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,@PathVariable Integer categoryId) {
		return ResponseEntity.ok(this.categoryService.updateCategory(categoryDTO, categoryId));
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully",true),HttpStatus.OK);
	}
}
