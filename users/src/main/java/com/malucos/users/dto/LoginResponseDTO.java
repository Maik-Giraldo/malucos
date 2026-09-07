package com.malucos.users.dto;

import lombok.Data;

@Data 
public class LoginResponseDTO {
    /**
     * Jwt de usuario logueado
     */
    private String jwt;

    /**
     * Información del usuario
     */
    private UserDTO user;
}
