package com.malucos.usuarios.dto;

import lombok.Data;

@Data
public class HttpGlobalResponse<T> {
    private String message;

    private T data;
}