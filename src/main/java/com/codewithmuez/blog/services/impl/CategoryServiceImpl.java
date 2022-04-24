package com.codewithmuez.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithmuez.blog.entities.Category;
import com.codewithmuez.blog.exceptions.ResourceNotFoundException;
import com.codewithmuez.blog.payloads.CategoryDTO;
import com.codewithmuez.blog.repositories.CategoryRepository;
import com.codewithmuez.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	public CategoryRepository categoryRepository;
	
	@Autowired
	public ModelMapper modelMapper;
	
	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Category category = this.dtoTOCategory(categoryDTO);
		Category savedCategory = this.categoryRepository.save(category);
		return this.categoryToDTO(savedCategory);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId",categoryId));
		category.setCategoryTitle(categoryDTO.getCategoryTitle());
		category.setCategoryDescription(categoryDTO.getCategoryDescription());
		Category updatedCategory = this.categoryRepository.save(category);
		CategoryDTO cat = this.categoryToDTO(updatedCategory);
		return cat;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		this.categoryRepository.delete(category);
	}

	@Override
	public CategoryDTO getCategoryById(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		return this.categoryToDTO(category);
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		List<Category> categories = this.categoryRepository.findAll();
		List<CategoryDTO> categoryDTOs = categories.stream().map(category -> this.categoryToDTO(category)).collect(Collectors.toList());
		return categoryDTOs;
	}
	
	private Category dtoTOCategory(CategoryDTO categoryDTO) {
		Category category = this.modelMapper.map(categoryDTO, Category.class);
		return category;
	}
	private CategoryDTO categoryToDTO(Category category) {
		CategoryDTO categoryDTO = this.modelMapper.map(category, CategoryDTO.class);
		return categoryDTO;
	}
	

}
