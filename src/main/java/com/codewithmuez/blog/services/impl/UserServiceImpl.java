package com.codewithmuez.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codewithmuez.blog.config.AppConstant;
import com.codewithmuez.blog.entities.Role;
import com.codewithmuez.blog.entities.User;
import com.codewithmuez.blog.exceptions.ResourceNotFoundException;
import com.codewithmuez.blog.payloads.CategoryDTO;
import com.codewithmuez.blog.payloads.UserDTO;
import com.codewithmuez.blog.repositories.RoleRepository;
import com.codewithmuez.blog.repositories.UserRepository;
import com.codewithmuez.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user= this.dtotoUser(userDTO);
		User savedUser = this.userRepository.save(user);
		return this.userToDTO(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id",userId));
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setAbout(userDTO.getAbout());
		User updatedUser = this.userRepository.save(user);
		UserDTO userDTO1 =this.userToDTO(updatedUser);
		return userDTO1;
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "Id",userId));
		return this.userToDTO(user);
	}

//	@Override
//	public List<UserDTO> getAllUsers() {
//		List<User> users = this.userRepository.findAll();
//		List<UserDTO> userDTOs = users.stream().map(user->this.userToDTO(user)).collect(Collectors.toList());
//		return userDTOs;
//	}
	
	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "Id",userId));
		this.userRepository.delete(user);
	}
	
//	DTO to entity
//	private User dtotoUser(UserDTO userDTO) {
//		User user =new User();
//		user.setId(userDTO.getId());
//		user.setName(userDTO.getName());
//		user.setEmail(userDTO.getEmail());
//		user.setAbout(userDTO.getAbout());
//		user.setPassword(userDTO.getPassword());
//		return user;
//	}
	
//	Now with the help of modelMapper
	private User dtotoUser(UserDTO userDTO) {
		User user = this.modelMapper.map(userDTO, User.class);
		return user;
	}
	
//	entity to DTO
//	public UserDTO userToDTO(User user) {
//		UserDTO userDTO = new UserDTO();
//		userDTO.setId(user.getId());
//		userDTO.setName(user.getName());
//		userDTO.setEmail(user.getEmail());
//		userDTO.setAbout(user.getAbout());
//		userDTO.setPassword(user.getPassword());
//		return userDTO;
//	}
	
// Now with the help of modelMapper
	private UserDTO userToDTO(User user) {
		UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users =	this.userRepository.findAll();
		List<UserDTO> userDTOs = users.stream().map(user -> this.userToDTO(user)).collect(Collectors.toList());
		return userDTOs;
	}

	@Override
	public UserDTO registerNewUser(UserDTO userDto) {
		User user = this.modelMapper.map(userDto, User.class);

		// encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		// roles
		Role role = this.roleRepo.findById(AppConstant.NORMAL_USER).get();

		user.getRoles().add(role);

		User newUser = this.userRepository.save(user);

		return this.modelMapper.map(newUser, UserDTO.class);
	}

}
