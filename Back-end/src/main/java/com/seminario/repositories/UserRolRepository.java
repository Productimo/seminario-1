package com.seminario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seminario.entitys.UserRol;

public interface UserRolRepository extends JpaRepository<UserRol, Long> {
	
	UserRol findByRol(String rol);
}
