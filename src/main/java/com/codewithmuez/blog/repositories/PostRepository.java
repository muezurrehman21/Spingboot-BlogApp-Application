package com.codewithmuez.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithmuez.blog.entities.Category;
import com.codewithmuez.blog.entities.Post;
import com.codewithmuez.blog.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);
}
