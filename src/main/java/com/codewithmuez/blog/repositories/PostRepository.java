package com.codewithmuez.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codewithmuez.blog.entities.Category;
import com.codewithmuez.blog.entities.Post;
import com.codewithmuez.blog.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	Page<Post> findByUser(User user,Pageable pageable);
	
	Page<Post> findByCategory(Category category,Pageable pageable);
	
	@Query("select p from Post p where p.title like :key")
	List<Post> findMyTitle(@Param("key")String title);


}
