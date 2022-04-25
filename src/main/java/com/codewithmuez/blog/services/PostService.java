package com.codewithmuez.blog.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.codewithmuez.blog.entities.Post;
import com.codewithmuez.blog.payloads.PostDTO;
import com.codewithmuez.blog.payloads.PostResponse;

@Service
public interface PostService {

//	create
	PostDTO createPost(PostDTO postDTO,Integer userId,Integer categoryId);
	
//	update
	PostDTO updatePost(PostDTO postDTO, Integer postId,Integer categoryId,Integer userId);
	
//	delete
	void deletePost(Integer postId);
	
//	get All posts
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy,String sortDir);
	
//	get Single post
	PostDTO getSinglePost(Integer postId);
	
//	get All post By Category
	PostResponse getPostByCategory(Integer categoryId,Integer pageNumber,Integer pageSize);
	
//	get All post By User
	PostResponse getAllPostByUser(Integer userId,Integer pageNumber,Integer pageSize);
	
//	get Search Post
	List<PostDTO> searchPosts(String keywords);
}
