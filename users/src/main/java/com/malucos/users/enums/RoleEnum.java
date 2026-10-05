package com.malucos.users.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
public enum RoleEnum {
    ADMIN(1L),
    CLIENTE(2L),
    VENDEDOR(3L);

    private final Long id;

    public Long getId() {
        return id;
    }
}
