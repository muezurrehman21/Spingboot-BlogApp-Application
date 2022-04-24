package com.codewithmuez.blog.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.codewithmuez.blog.entities.Post;
import com.codewithmuez.blog.payloads.PostDTO;

@Service
public interface PostService {

//	create
	PostDTO createPost(PostDTO postDTO,Integer userId,Integer categoryId);
	
//	update
	PostDTO updatePost(PostDTO postDTO, Integer postId,Integer categoryId,Integer userId);
	
//	delete
	void deletePost(Integer postId);
	
//	get All posts
	List<PostDTO> getAllPosts(Integer pageNumber, Integer pageSize);
	
//	get Single post
	PostDTO getSinglePost(Integer postId);
	
//	get All post By Category
	List<PostDTO> getPostByCategory(Integer categoryId);
	
//	get All post By User
	List<PostDTO> getAllPostByUser(Integer userId);
	
//	get Search Post
	List<PostDTO> searchPosts(String keywords);
}
