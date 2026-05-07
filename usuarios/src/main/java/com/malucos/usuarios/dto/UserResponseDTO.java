package com.malucos.usuarios.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    /**
     * Mensaje
     */
    private String message;

    /**
     * Nombre del usuario
     */
    private Long id;

    /**
     * Nombre del usuario
     */
    private String name;

    /**
     * Apellido del usuario
     */
    private String lastName;

    /**
     * Edad del usuario
     */
    private Long age;

    /**
     * Correo del usuario
     */
    private String email;
}
