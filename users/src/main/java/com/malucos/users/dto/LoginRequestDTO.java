package com.malucos.users.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    /**
     * Nombre de usuario
     */
    private String user;

    /**
     * Contraseña del usuario
     */
    private String password;
}
