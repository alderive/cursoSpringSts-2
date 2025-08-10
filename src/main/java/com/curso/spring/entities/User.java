package com.curso.spring.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description="Entidad que representa usario en el sistema")
public class User {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Schema(description = "Identificador único del usuario", example = "101")
	private Integer iduser;	
	
	@NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Schema(description = "Nombre completo del usuario", example = "Juan García")
	private String name;
	
	
	@Email(message = "El correo electrónico no tiene un formato válido")
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Schema(description = "Correo electrónico del usuario", example = "alde@example.com")
    private String email;

    @Min(value = 0, message = "El estado debe ser igual o mayor a 0")
    @Max(value = 1, message = "El estado debe ser 0 o 1")
    @Schema(description = "Estado del usuario (1 = activo, 0 = inactivo)", example = "1")
    private Integer status;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Schema(description = "Contraseña del usuario (encriptada)", example = "abc123XYZ!")
    private String password;

    @Version
    @Schema(description = "Versión de la entidad para control de concurrencia", example = "3")
    private Integer version;



}
