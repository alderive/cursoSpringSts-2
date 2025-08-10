package com.curso.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.spring.entities.User;

/**
 * Clase para acceso a entidad User
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

		
	
	
	 

}
