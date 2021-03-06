package com.codewithmuez.blog.controllers;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.codewithmuez.blog.config.AppConstant;
import com.codewithmuez.blog.payloads.ApiResponse;
import com.codewithmuez.blog.payloads.PostDTO;
import com.codewithmuez.blog.payloads.PostResponse;
import com.codewithmuez.blog.services.FileService;
import com.codewithmuez.blog.services.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name="post")
@RequestMapping("api/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	
	@Operation(summary = "Create Post")
	@PostMapping("user/{userId}/category/{categoryId}/")
	public ResponseEntity<PostDTO> createPost( 
			@Valid
			@RequestBody PostDTO postDTO,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId){
		
	PostDTO createPost = this.postService.createPost(postDTO, userId, categoryId);
	return new ResponseEntity<PostDTO>(createPost, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{postId}/")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully",true),HttpStatus.OK);
	}
	
	@PutMapping("{postId}/{categoryId}/{userId}/")
	public ResponseEntity<PostDTO> updatedPost(
			@RequestBody PostDTO postDTO,
			@PathVariable Integer postId,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId){
		return ResponseEntity.ok(this.postService.updatePost(postDTO, postId, categoryId, userId));
	}
	
	@GetMapping("/user/{userId}/")
	public ResponseEntity<PostResponse> getAllPostsByUser(
			@PathVariable Integer userId,
			@RequestParam(value = "pageNumber",defaultValue = "1",required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize){
		PostResponse posts =  this.postService.getAllPostByUser(userId,pageNumber,pageSize);
		return new ResponseEntity<PostResponse>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}/")
	public ResponseEntity<PostResponse> getAllPostsByCategory(
			@PathVariable Integer categoryId,
			@RequestParam(value = "pageNumber",defaultValue = "1",required =false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "5",required =false) Integer pageSize){
		PostResponse posts =  this.postService.getPostByCategory(categoryId,pageNumber,pageSize);
		return new ResponseEntity<PostResponse>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/{postId}/")
	public ResponseEntity<PostDTO> getSingPostById(@PathVariable Integer postId){
		PostDTO post = this.postService.getSinglePost(postId);
		return new ResponseEntity<PostDTO>(post,HttpStatus.OK);
		
	}
	@GetMapping("/getAllPosts/")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required =false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_SIZE,required =false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY,required= false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR,required = false) String sortDir ){
		PostResponse posts =  this.postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(posts,HttpStatus.OK);
	}
	@GetMapping("search/{title}")
	public ResponseEntity<List<PostDTO>> getAllPostByTitle(@PathVariable String title){
		List<PostDTO> posts = this.postService.searchPosts(title);
		return new ResponseEntity<List<PostDTO>>(posts,HttpStatus.OK);
	}
	
	//post Image Upload
	@PostMapping("/image/upload/{postId}/{categoryId}/{userId}/")
	public ResponseEntity<PostDTO> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			) throws IOException{
	String fileName = this.fileService.uploadImage(path, image);
	PostDTO postDTO = this.postService.getSinglePost(postId);
	postDTO.setImageName(fileName);
	PostDTO updatePost = this.postService.updatePost(postDTO, postId, categoryId, userId);
	return new ResponseEntity<PostDTO>(updatePost,HttpStatus.OK);
	}

}
