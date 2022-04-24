package com.codewithmuez.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.codewithmuez.blog.entities.Category;
import com.codewithmuez.blog.entities.Post;
import com.codewithmuez.blog.entities.User;
import com.codewithmuez.blog.exceptions.ResourceNotFoundException;
import com.codewithmuez.blog.payloads.PostDTO;
import com.codewithmuez.blog.repositories.CategoryRepository;
import com.codewithmuez.blog.repositories.PostRepository;
import com.codewithmuez.blog.repositories.UserRepository;
import com.codewithmuez.blog.services.PostService;

@Service
public class PostServiceImple implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	

	@Override
	public PostDTO createPost(PostDTO postDTO,Integer userId,Integer categoryId) {
		User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "category Id", categoryId));
		Post post = this.modelMapper.map(postDTO, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost = this.postRepository.save(post);
		return this.modelMapper.map(newPost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO,Integer postId,Integer categoryId,Integer userId) {
		Post post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
		post.setContent(postDTO.getContent());
		post.setTitle(postDTO.getTitle());
		post.setAddedDate(new Date());
		post.setImageName(postDTO.getImageName());
		post.setCategory(this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "category Id", categoryId)));
		post.setUser(this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "user Id", userId)));
		Post savePost = this.postRepository.save(post);
		PostDTO updatedPost = this.modelMapper.map(savePost, PostDTO.class);
		return updatedPost;
	}

	@Override
	public void deletePost(Integer postId) {
		Post deletePost = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
		this.postRepository.delete(deletePost);

	}

	@Override
	public List<PostDTO> getAllPosts(Integer pageNumber, Integer pageSize) {
		// TODO Auto-generated method stub
		Pageable p = PageRequest.of(pageNumber,pageSize);
		
		Page<Post> pagePost = this.postRepository.findAll(p);
		
		List<Post> posts = pagePost.getContent();
		
		List<PostDTO> postDTOs = posts.stream().map((post)->this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDTOs;
	}

	@Override
	public PostDTO getSinglePost(Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));
		PostDTO postDTO = this.modelMapper.map(post, PostDTO.class);
		return postDTO;
	}

	@Override
	public List<PostDTO> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));
		List<Post> posts = this.postRepository.findByCategory(category);
		List<PostDTO> postDTOs = posts.stream().map((post)->this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDTOs;
	}

	@Override
	public List<PostDTO> getAllPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user =this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "user Id", userId));
		List<Post> posts = this.postRepository.findByUser(user);
		List<PostDTO> postDTOs = posts.stream().map((post)->this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDTOs;
	}

	@Override
	public List<PostDTO> searchPosts(String keywords) {
		List<Post> getPostByTitle = this.postRepository.findByTitleContaining(keywords);
		List<PostDTO> postDTOs = getPostByTitle.stream().map((post)->this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDTOs;
	}

}