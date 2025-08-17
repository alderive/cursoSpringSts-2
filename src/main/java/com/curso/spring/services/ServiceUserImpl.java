package com.curso.spring.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.spring.entities.User;
import com.curso.spring.exceptions.UsuarioExistenteException;
import com.curso.spring.exceptions.UsuarioNoExistenteException;
import com.curso.spring.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class ServiceUserImpl implements ServiceUser {
	
	@Autowired
	UserRepository repo;
	
	
	@Override
	/**
	 * @param user  Usuario a dar de alta
	 * @return  Objeto User persistido
	 * @throws Exception Si ocurre un problema al momento de persistir el usuario
	 */
	public User save(User user) throws UsuarioExistenteException {
		
		if(user.getIduser() != null){
			throw new UsuarioExistenteException("El usuario ya existe.");
		}
		return repo.save(user); 
	}

	@Override
	/**
	 * @return Lista de Usuarios encontrados
	 * @throws Exception Si ocurre un problema al momento de buscar usuarios
	 */
	public List<User> findAll() throws Exception {
		return repo.findAll();
	}

	@Override
	/**
	 * @param id Identificador de usuario a buscar
	 * @return Objeto User si es econtrado una coincidencia con el ID utilizado
	 * @throws Exception Si ocurre un problema al momento de buscar el usuario
	 */
	public User getUserById(Integer id) throws UsuarioNoExistenteException {
		
		Optional<User> optionalUser = repo.findById(id);

		if (optionalUser.isPresent()) {
	        return optionalUser.get();
	    } else {
	        throw new UsuarioNoExistenteException("Usuario con ID " + id + " no encontrado");
	    }

	}
	
	/**
	 * @param id Identificador de usuario a eliminar
	 * @throws Exception Si ocurre un problema al momento de eliminar el usuario
	 */	
	  @Override 
	  public void deleteUserById(Integer id) throws Exception {
		  repo.deleteById(id);
	  }

	
	 /**
	 * @param id Identificador de usuario a eliminar
	 * @throws Exception Si ocurre un problema al momento de eliminar el usuario
	 */  
	 @Override
	 public List<User> getAllUser() throws Exception {
		 
		 List<User> lista = repo.findAll();
		return lista;
	 }

	 @Override
	 public User updateUser(Integer id, User updatedUser) throws EntityNotFoundException, Exception {
		 
		 Optional<User> optionalUser = repo.findById(id);

		    if (optionalUser.isPresent()) {
		        User existingUser = optionalUser.get();

		        // Actualizamos campos relevantes
		        existingUser.setName(updatedUser.getName());
		        existingUser.setEmail(updatedUser.getEmail());
		        existingUser.setStatus(updatedUser.getStatus());
		        existingUser.setPassword(updatedUser.getPassword());

		        // La versión se maneja automáticamente por JPA (@Version)
		        return repo.save(existingUser);
		    } else {
		        throw new EntityNotFoundException("Usuario con ID " + id + " no encontrado");
		    }

	 }

	

}

