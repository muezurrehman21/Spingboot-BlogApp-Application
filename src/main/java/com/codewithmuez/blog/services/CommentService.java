package com.codewithmuez.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codewithmuez.blog.payloads.CommentDTO;

@Service
public interface CommentService {
	
	CommentDTO createComment(CommentDTO commentDTO,Integer userId,Integer postId);
	
	CommentDTO updateDTO(CommentDTO commentDTO,Integer commentId,int userId,int postId);
	
	void deleteComment(Integer commentId);
	
	List<CommentDTO> getAllComments();
	
	CommentDTO getCommentById(Integer commentId);
	
}
