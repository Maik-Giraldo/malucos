package com.malucos.users.dto;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    /**
     * Nombre de usuario
     */
    private String username;

    /**
     * Correo del usuario
     */
    private String email;

    /**
     * Contraseña del usuario
     */
    private String password;

    /**
     * rol del usuario
     */
    private Long rolId;
}
