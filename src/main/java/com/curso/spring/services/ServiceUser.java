package com.curso.spring.services;

import java.util.List;
import java.util.Optional;


import com.curso.spring.entities.User;
import com.curso.spring.exceptions.UsuarioExistenteException;
import com.curso.spring.exceptions.UsuarioNoExistenteException;

import jakarta.persistence.EntityNotFoundException;


/**
 * Clase para repositorio User
 */

public interface ServiceUser {
	
		
	/**
	 * @param user  Usuario a dar de alta
	 * @return  Objeto User persistido
	 * @throws Exception Si ocurre un problema al momento de persistir el usuario
	 */
	public User save(User user) throws UsuarioExistenteException;
	
	/**
	 * @return Lista de Usuarios encontrados
	 * @throws Exception Si ocurre un problema al momento de buscar usuarios
	 */
	public List<User> findAll() throws Exception; 
	
	/**
	 * @param id Identificador de usuario a buscar
	 * @return Objeto User si es econtrado una coincidencia con el ID utilizado
	 * @throws Exception Si ocurre un problema al momento de buscar el usuario
	 */
	public User getUserById(Integer id) throws UsuarioNoExistenteException;
	
	/**
	 * @param id Identificador de usuario a eliminar
	 * @throws Exception Si ocurre un problema al momento de eliminar el usuario
	 */
	public void deleteUserById(Integer id) throws Exception; 
	
	/**
	 * @return Lista de todos los usuarios
	 * @throws Exception Si ocurre un problema al momento de consultar usuarios
	 */
	public List<User> getAllUser() throws Exception; 
	
	
	/**
	 * @return Lista de todos los usuarios
	 * @throws Exception Si ocurre un problema al momento de consultar usuarios
	 */
	public User updateUser(Integer id, User updatedUser) throws EntityNotFoundException,Exception; 
}