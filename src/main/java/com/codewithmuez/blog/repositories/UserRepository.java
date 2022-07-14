package com.codewithmuez.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.codewithmuez.blog.entities.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	
	Optional<User> findByEmail(String email);
}
