package com.curso.spring.vo;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * Clase poara respuesta de servicio
 */

@Data
public class ApiResponse {

	
private Map<String, Object> userMap = new HashMap<>();
    

    /**
     * @param userMap Objeto con respuesta del servicio solicitado
     */
    public ApiResponse(final Map<String, Object> userMap) {}
    
    
    /**
     * Constructor
     */
    public ApiResponse() {}

}
