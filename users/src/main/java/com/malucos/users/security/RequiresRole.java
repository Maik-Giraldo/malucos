package com.malucos.users.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.malucos.users.enums.RoleEnum;

// Define donde quiero usar mi anotación (metodo)
@Target(ElementType.METHOD)
// RUNTIME: disponible en tiempo de ejecución, en todo momento (esto es necesario para que el interceptor lo lea)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresRole {
    RoleEnum[] value();
}