package com.codewithmuez.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codewithmuez.blog.payloads.ApiResponse;
import com.codewithmuez.blog.payloads.CommentDTO;
import com.codewithmuez.blog.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;

@Controller
@RequestMapping("api/Comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	
	@Operation(summary = "Create Comment")
	@PostMapping("createComment/{userId}/postId/{postId}")
	public ResponseEntity<CommentDTO> createComment(
			@RequestBody CommentDTO commentDTO,
			@PathVariable Integer userId,
			@PathVariable Integer postId){
	CommentDTO createComment = this.commentService.createComment(commentDTO, userId, postId);
	return new ResponseEntity<CommentDTO>(createComment,HttpStatus.ACCEPTED);
	}
	
	@Operation(summary = "update Comment")
	@PostMapping("updateComment/{commentId}/user/{userId}/post/{postId}")
	public ResponseEntity<CommentDTO> updateComment(
			@RequestBody CommentDTO commentDTO,
			@PathVariable int commentId,
			@PathVariable int userId,
			@PathVariable int postId){
		CommentDTO saveComment = this.commentService.updateDTO(commentDTO, commentId, userId, postId);
		return new ResponseEntity<CommentDTO>(saveComment,HttpStatus.UPGRADE_REQUIRED);
	}
	@Operation(summary = "deleteComment")
	@DeleteMapping("deleteComment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(
			@PathVariable int commentId
			) {
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment is deleted",true),HttpStatus.OK);
	}
	
	@Operation(summary = "All Comments")
	@GetMapping("allComment")
	public ResponseEntity<List<CommentDTO>> allComments(){
		List<CommentDTO> allComments = this.commentService.getAllComments();
		return new ResponseEntity<List<CommentDTO>>(allComments,HttpStatus.OK);
	}

}
