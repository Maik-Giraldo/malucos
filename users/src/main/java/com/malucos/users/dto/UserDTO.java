package com.malucos.users.dto;

import com.malucos.users.entity.Roles;

import lombok.Data;

@Data
public class UserDTO {
    /**
     * Id del usuario
     */
    private Long id;

    /**
     * Nombre de usuario
     */
    private String username;

    /**
     * Correo del usuario
     */
    private String email;

    /**
     * rol del usuario
     */
    private String rolId;
}
