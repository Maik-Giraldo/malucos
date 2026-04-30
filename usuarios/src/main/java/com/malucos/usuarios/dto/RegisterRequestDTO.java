package com.malucos.usuarios.dto;

import lombok.Data;

@Data
public class RegisterRequestDTO {
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
}