package com.codewithmuez.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codewithmuez.blog.payloads.CategoryDTO;

@Service
public interface CategoryService {
	
//	create
	public CategoryDTO createCategory(CategoryDTO categoryDTO);
	
//	update
	public CategoryDTO updateCategory(CategoryDTO categoryDTO,Integer categoryId);
	
//	delete
	public void deleteCategory(Integer categoryId);
	
//	get
	public CategoryDTO getCategoryById(Integer categoryId);
	
//	getAllCategory
	public List<CategoryDTO> getAllCategory();

}
