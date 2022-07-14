package com.codewithmuez.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithmuez.blog.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> 
{
	
}
