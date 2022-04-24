package com.codewithmuez.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithmuez.blog.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{


}
