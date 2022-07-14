package com.codewithmuez.blog.payloads;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDTO {
	
//	private Integer postId;
	
	@Size(min=4, message = "tile must be greater than 4")
	@NotEmpty(message = "title cannot be empty")
	private String title;
	
	private String content;
	
	private String imageName;
	
	private CategoryDTO category;
	
	private UserDTO user;
	
	private Set<CommentDTO> comments =  new HashSet<>();
}
