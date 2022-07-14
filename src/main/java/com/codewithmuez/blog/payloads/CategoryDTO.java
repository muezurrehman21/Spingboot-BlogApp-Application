package com.codewithmuez.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {
	private Integer categoryId;
	
	@NotEmpty
	@Size(min = 4, message = "title must be greater than 4")
	private String categoryTitle;
	
	@NotEmpty
	@Size(min = 4, message = "Description must be greater than 4")
	private String categoryDescription; 

}
