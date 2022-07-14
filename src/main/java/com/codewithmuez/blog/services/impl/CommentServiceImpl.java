package com.codewithmuez.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithmuez.blog.entities.Comment;
import com.codewithmuez.blog.entities.Post;
import com.codewithmuez.blog.entities.User;
import com.codewithmuez.blog.exceptions.ResourceNotFoundException;
import com.codewithmuez.blog.payloads.CommentDTO;
import com.codewithmuez.blog.repositories.CommentRepository;
import com.codewithmuez.blog.repositories.PostRepository;
import com.codewithmuez.blog.repositories.UserRepository;
import com.codewithmuez.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper; 
	
	@Override	
	public CommentDTO createComment(CommentDTO commentDTO,Integer userId,Integer postId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "user", userId));
		Post post = this.postRepository.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post", "Post", postId));
		Comment comment = this.modelMapper.map(commentDTO, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		Comment savedComment = this.commentRepository.save(comment);
		return this.modelMapper.map(savedComment, CommentDTO.class);
	}

	@Override
	public CommentDTO updateDTO(CommentDTO commentDTO, Integer commentId,int userId,int postId) {
		Comment comment = this.commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "comment", commentId));
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "user", userId));
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post", postId));
		comment.setContent(commentDTO.getContent());
		comment.setUser(user);
		comment.setPost(post);
		Comment saveComment = this.commentRepository.save(comment);
		return this.commentToDTO(saveComment);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "CommentId", commentId));
		this.commentRepository.delete(comment);
	}

	@Override
	public List<CommentDTO> getAllComments() {
		List<Comment> comments = this.commentRepository.findAll();
		List<CommentDTO> commentDTOs = comments.stream().map((comment)->this.commentToDTO(comment)).collect(Collectors.toList());
		return commentDTOs;
	}

	@Override
	public CommentDTO getCommentById(Integer commentId) {
		Comment comment = this.commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "commentID", commentId));;
		return this.commentToDTO(comment);
	}
	
	private CommentDTO commentToDTO(Comment comment) {
		CommentDTO commentDTO =  this.modelMapper.map(comment, CommentDTO.class);
		return commentDTO;	
	}
	
	private Comment DTOtoComment(CommentDTO commentDTO) {
		Comment comment =this.modelMapper.map(commentDTO, Comment.class);
		return comment;
	}

}
