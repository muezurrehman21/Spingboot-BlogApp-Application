package com.codewithmuez.blog.payloads;

import java.util.List;

import com.codewithmuez.blog.entities.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
	
	private List<PostDTO> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean lastPage;
}
