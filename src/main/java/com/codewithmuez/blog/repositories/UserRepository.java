package com.codewithmuez.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithmuez.blog.entities.User;

public interface UserRepository extends JpaRepository<User,Integer> {

}
