package com.codewithmuez.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.codewithmuez.blog.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	
	private Set<RolesDTO> roles = new HashSet<>();

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
