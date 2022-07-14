package com.codewithmuez.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithmuez.blog.entities.Role;

public interface RoleRepository  extends JpaRepository<Role, Integer>{

}
