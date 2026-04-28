package com.malucos.usuarios.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity // Representacion de la tabla de mi base de datos
@Table(name = "users") // Nombre de la tabla
@Data // Getter y Setter
public class Users {

    @Id // Llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENTENT
    private Long id;

    @Column(name = "name") // Nombre de la columna en la bd
    private String name; // Variable (alias) usado en springboot referente a esa columna

    @Column(name = "lastname") // Nombre de la columna en la bd
    private String lastname; // Variable (alias) usado en springboot referente a esa columna

    @Column(name = "age") // Nombre de la columna en la bd
    private Long age; // Variable (alias) usado en springboot referente a esa columna
}