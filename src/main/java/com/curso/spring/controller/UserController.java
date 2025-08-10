package com.curso.spring.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.spring.entities.User;
import com.curso.spring.exceptions.UsuarioExistenteException;
import com.curso.spring.exceptions.UsuarioNoExistenteException;
import com.curso.spring.services.ServiceUser;
import com.curso.spring.vo.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;





@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuarios", description = "Operaciones CRUD sobre usuarios")
public class UserController {

@Autowired
ServiceUser service;

	/**
	 * Method for save record of entity User
	 * 
	 * @param user is the object to save
	 * @return ApiResponse with Status Code Message
	 *         Information about result of service invoked Data Object resulted
	 */
	@Operation(summary = "Crear un nuevo usuario")
	@PostMapping("/save")
	public ResponseEntity<ApiResponse> createUser(@RequestBody @Valid User user) {
		ApiResponse response = new ApiResponse();
		Map<String, Object> retorno = new HashMap<>();
		try {
			User usr = service.save(user);
			retorno.put("Estatus", 201);
			retorno.put("Mensaje", "Usuario creado correctamente");
			retorno.put("Data", usr);
		} catch (UsuarioExistenteException e) {
			retorno.put("Estatus", 400);
			retorno.put("Mensaje", e.getMessage());
			retorno.put("Data", user);
		} catch (Exception e) {
			retorno.put("Estatus", 500);
			retorno.put("Mensaje", e.getCause());
			retorno.put("Data", user);
		}
		response.setUserMap(retorno);
        return ResponseEntity.badRequest().body(response);
	}
	
	
		@Operation(summary = "Obtener usuario por ID")
	    @GetMapping("/get/{id}")
	    public ResponseEntity<ApiResponse> getUserById(@PathVariable Integer id) {
		 	ApiResponse response = new ApiResponse();
			Map<String, Object> retorno = new HashMap<>();
	        try {
				User usr = service.getUserById(id);
				retorno.put("Estatus", 201);
				retorno.put("Mensaje", "Usuario obtenido correctamente");
				retorno.put("Data", usr);
				response.setUserMap(retorno);
			} catch (UsuarioNoExistenteException e) {
				retorno.put("Estatus", 400);
				retorno.put("Mensaje", e.getMessage());
				retorno.put("Data", null);
			} catch (Exception e) {
				retorno.put("Estatus", 500);
				retorno.put("Mensaje", e.getCause());
				retorno.put("Data", null);
			}
	        response.setUserMap(retorno);
	        return ResponseEntity.badRequest().body(response);
	    }
		
		@Operation(summary = "Obtener todos los usuarios")
	    @GetMapping
	    public ResponseEntity<ApiResponse> getAllUsers() {
			ApiResponse response = new ApiResponse();
			Map<String, Object> retorno = new HashMap<>();
			try {
				
				retorno.put("Estatus", 201);
				retorno.put("Mensaje", "Usuario obtenido correctamente");
				retorno.put("Data", service.getAllUser());
				response.setUserMap(retorno);
			} catch (Exception e) {
				retorno.put("Estatus", 500);
				retorno.put("Mensaje", e.getCause());
				retorno.put("Data", null);
			}
	        response.setUserMap(retorno);
	        return ResponseEntity.badRequest().body(response);
	    }

		
		@Operation(summary = "Borrar usuario por ID")
	    @GetMapping("delete/{id}")
	    public ResponseEntity<ApiResponse> delUserById(@PathVariable Integer id) {
		 	ApiResponse response = new ApiResponse();
			Map<String, Object> retorno = new HashMap<>();
	        try {
				service.deleteUserById(id);
				retorno.put("Estatus", 201);
				retorno.put("Mensaje", "Usuario eliminado correctamente");
				retorno.put("Data", id);				
			} catch (Exception e) {
				retorno.put("Estatus", 500);
				retorno.put("Mensaje", e.getCause());
				retorno.put("Data", null);
			}
	        response.setUserMap(retorno);
	        return ResponseEntity.badRequest().body(response);
	    }
		
		@Operation(summary = "Actualiza usuario")
	    @GetMapping("/{id}")
	    public ResponseEntity<ApiResponse> updateUser(@PathVariable Integer id, @Valid @RequestBody User user) {
		 	ApiResponse response = new ApiResponse();
			Map<String, Object> retorno = new HashMap<>();
	        try {
				service.updateUser(id, user);
				retorno.put("Estatus", 201);
				retorno.put("Mensaje", "Usuario actualizado correctamente");
				retorno.put("Data", user);				
	        } catch (UsuarioNoExistenteException e) {
				retorno.put("Estatus", 400);
				retorno.put("Mensaje", e.getMessage());
				retorno.put("Data", null);
			} catch (Exception e) {
				retorno.put("Estatus", 500);
				retorno.put("Mensaje", e.getCause());
				retorno.put("Data", null);
			}
	        response.setUserMap(retorno);
	        return ResponseEntity.badRequest().body(response);
	    }
		
		


}
