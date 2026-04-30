package com.malucos.usuarios.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malucos.usuarios.dto.MessageResponseDTO;
import com.malucos.usuarios.dto.RegisterRequestDTO;
import com.malucos.usuarios.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController // Controlador de tipo Rest
@RequestMapping("/users") // Mapeo de solicitud para esta clase
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public MessageResponseDTO createUser(@RequestBody RegisterRequestDTO request) {
        MessageResponseDTO response = userService.createUser(request);
        return response;
    }
}