package com.curso.spring.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.curso.spring.entities.User;
import com.curso.spring.exceptions.UsuarioExistenteException;
import com.curso.spring.exceptions.UsuarioNoExistenteException;
import com.curso.spring.services.ServiceUser;
import com.curso.spring.vo.ApiResponse;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
	
	 	@InjectMocks
	    private UserController userController;

	    @Mock
	    private ServiceUser serviceUser;



	    @Test
	    void createUser_Returns201_WhenUserIsSavedSuccessfully() throws Exception {
	        // Arrange
	        User user = new User(1, "Juan", "juan@example.com", 1, "securePass123", 1);
	        when(serviceUser.save(any(User.class))).thenReturn(user);

	        // Act
	        ResponseEntity<ApiResponse> response = userController.createUser(user);

	        // Assert
	        ApiResponse apiResponse = response.getBody();
	        Map<String, Object> result = apiResponse.getUserMap();

	        assertEquals(201, result.get("Estatus"));
	        assertEquals("Usuario creado correctamente", result.get("Mensaje"));
	        assertEquals(user, result.get("Data"));
	    }

	    @Test
	    void createUser_Returns400_WhenUsuarioExistenteExceptionIsThrown() throws Exception {
	        // Arrange
	        User user = new User(1, "Juan", "juan@example.com", 1, "securePass123", 1);
	        when(serviceUser.save(any(User.class))).thenThrow(new UsuarioExistenteException("Usuario ya existe"));

	        // Act
	        ResponseEntity<ApiResponse> response = userController.createUser(user);

	        // Assert
	        ApiResponse apiResponse = response.getBody();
	        Map<String, Object> result = apiResponse.getUserMap();

	        assertEquals(400, result.get("Estatus"));
	        assertEquals("Usuario ya existe", result.get("Mensaje"));
	        assertEquals(user, result.get("Data"));
	    }
	    
	    
	  //--------------------------------------------------------------
		
	  		@Test
	  	    void getUserById_Returns201_WhenUserExists() {
	  	        // Arrange
	  	        Integer userId = 1;
	  	        User user = new User(userId, "Juan", "juan@example.com", 1, "securePass123", 1);
	  	        when(serviceUser.getUserById(userId)).thenReturn(user);

	  	        // Act
	  	        ResponseEntity<ApiResponse> response = userController.getUserById(userId);

	  	        // Assert
	  	        ApiResponse apiResponse = response.getBody();
	  	        Map<String, Object> result = apiResponse.getUserMap();

	  	        assertEquals(201, result.get("Estatus"));
	  	        assertEquals("Usuario obtenido correctamente", result.get("Mensaje"));
	  	        assertEquals(user, result.get("Data"));
	  	    }

	  	    @Test
	  	    void getUserById_Returns400_WhenUserDoesNotExist() {
	  	        // Arrange
	  	        Integer userId = 999;
	  	        when(serviceUser.getUserById(userId)).thenThrow(new UsuarioNoExistenteException("Usuario no encontrado"));

	  	        // Act
	  	        ResponseEntity<ApiResponse> response = userController.getUserById(userId);

	  	        // Assert
	  	        ApiResponse apiResponse = response.getBody();
	  	        Map<String, Object> result = apiResponse.getUserMap();

	  	        assertEquals(400, result.get("Estatus"));
	  	        assertEquals("Usuario no encontrado", result.get("Mensaje"));
	  	        assertNull(result.get("Data"));
	  	    }
	  	    
	  	    
	  	  @Test
	      void getAllUsers_Returns201_WhenUsersExist() throws Exception {
	          // Arrange
	          List<User> users = List.of(
	              new User(1, "Ana", "ana@example.com", 1, "pass123", 1),
	              new User(2, "Luis", "luis@example.com", 1, "pass456", 1)
	          );
	          when(serviceUser.getAllUser()).thenReturn(users);

	          // Act
	          ResponseEntity<ApiResponse> response = userController.getAllUsers();

	          // Assert
	          ApiResponse apiResponse = response.getBody();
	          Map<String, Object> result = apiResponse.getUserMap();

	          assertEquals(201, result.get("Estatus"));
	          assertEquals("Usuarios obtenidos correctamente", result.get("Mensaje"));
	          assertEquals(users, result.get("Data"));
	      }

	      @Test
	      void getAllUsers_Returns500_WhenExceptionThrown() throws Exception {
	          // Arrange
	          when(serviceUser.getAllUser()).thenThrow(new RuntimeException("Error interno"));

	          // Act
	          ResponseEntity<ApiResponse> response = userController.getAllUsers();

	          // Assert
	          ApiResponse apiResponse = response.getBody();
	          Map<String, Object> result = apiResponse.getUserMap();

	          assertEquals(500, result.get("Estatus"));
	          assertNotNull(result.get("Mensaje")); // Puede ser null si getCause() lo es
	          assertNotNull(result.get("Data"));
	      }
	      
	      @Test
	      void delUserById_Returns201_WhenDeletionSucceeds() throws Exception {
	          // Arrange
	          Integer userId = 1;
	          doNothing().when(serviceUser).deleteUserById(userId);

	          // Act
	          ResponseEntity<ApiResponse> response = userController.delUserById(userId);

	          // Assert
	          ApiResponse apiResponse = response.getBody();
	          Map<String, Object> result = apiResponse.getUserMap();

	          assertEquals(201, result.get("Estatus"));
	          assertEquals("Usuario eliminado correctamente", result.get("Mensaje"));
	          assertEquals(userId, result.get("Data"));
	      }

	      @Test
	      void delUserById_Returns500_WhenExceptionThrown() throws Exception {
	          // Arrange
	          Integer userId = 999;
	          doThrow(new Exception("Error al eliminar")).when(serviceUser).deleteUserById(userId);

	          // Act
	          ResponseEntity<ApiResponse> response = userController.delUserById(userId);

	          // Assert
	          ApiResponse apiResponse = response.getBody();
	          Map<String, Object> result = apiResponse.getUserMap();

	          assertEquals(500, result.get("Estatus"));
	          assertNotNull(result.get("Mensaje")); // Puede ser null si getCause() lo es
	          assertNull(result.get("Data"));
	      }
	      
	      @Test
	      void updateUser_Returns201_WhenUpdateSucceeds() {
	          // Arrange
	          Integer userId = 1;
	          User user = new User(userId, "Carlos", "carlos@example.com", 1, "securePass", 1);
	        
				//doNothing().when(serviceUser).updateUser(userId, user);
			
	          // Act
	          ResponseEntity<ApiResponse> response = userController.updateUser(userId, user);

	          // Assert
	          ApiResponse apiResponse = response.getBody();
	          Map<String, Object> result = apiResponse.getUserMap();

	          assertEquals(201, result.get("Estatus"));
	          assertEquals("Usuario actualizado correctamente", result.get("Mensaje"));
	          assertEquals(user, result.get("Data"));
	      }

	      @Test
	      void updateUser_Returns400_WhenUserNotFound() {
	          // Arrange
	          Integer userId = 999;
	          User user = new User(userId, "Desconocido", "no@existe.com", 1, "pass", 1);
	          try {
				doThrow(new UsuarioNoExistenteException("Usuario no existe")).when(serviceUser).updateUser(userId, user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	          // Act
	          ResponseEntity<ApiResponse> response = userController.updateUser(userId, user);

	          // Assert
	          ApiResponse apiResponse = response.getBody();
	          Map<String, Object> result = apiResponse.getUserMap();

	          assertEquals(400, result.get("Estatus"));
	          assertEquals("Usuario no existe", result.get("Mensaje"));
	          assertNull(result.get("Data"));
	      }

	      @Test
	      void updateUser_Returns500_WhenUnexpectedExceptionOccurs() throws Exception {
	          // Arrange
	          Integer userId = 2;
	          User user = new User(userId, "Error", "error@example.com", 1, "fail", 1);
	         
				doThrow(new Exception("Falla inesperada")).when(serviceUser).updateUser(userId, user);
			

	          // Act
	          ResponseEntity<ApiResponse> response = userController.updateUser(userId, user);

	          // Assert
	          ApiResponse apiResponse = response.getBody();
	          Map<String, Object> result = apiResponse.getUserMap();

	          assertEquals(500, result.get("Estatus"));
	          assertNotNull(result.get("Mensaje")); // Puede ser null si getCause() lo es
	          assertNull(result.get("Data"));
	      }





}
