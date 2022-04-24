package com.codewithmuez.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
	
	
	private int id;
	
	@NotEmpty
	@Size(min = 4,message = "Username must be min 4 of characters")
	private String name;
	
	@NotEmpty
	@Email(message = "Email address is not valid")
	private String email;
	
//	@Pattern(regexp = )
	@NotEmpty
	@Size(min = 3, message = "password must be min 4 of characters")
	private String password;
	
	@NotEmpty
	@Size(min = 3,message = "about must be min 4 of characters")
	private String about;
	
}
