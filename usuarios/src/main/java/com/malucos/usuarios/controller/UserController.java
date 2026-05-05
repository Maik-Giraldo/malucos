package com.malucos.usuarios.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malucos.usuarios.dto.MessageResponseDTO;
import com.malucos.usuarios.dto.RegisterRequestDTO;
import com.malucos.usuarios.dto.UserResponseDTO;
import com.malucos.usuarios.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController // Controlador de tipo Rest
@RequestMapping("/users") // Mapeo de solicitud para esta clase // Constructor
@RequiredArgsConstructor
public class UserController {
    
    // Inyección de dependencias
    private final UserService userService;

    @PostMapping("/create")
    public MessageResponseDTO createUser(@RequestBody RegisterRequestDTO request) {
        MessageResponseDTO response = userService.createUser(request);
        return response;
    }

    @GetMapping("/get-users")
    public List<UserResponseDTO> getUsers() { 
        List<UserResponseDTO> response = userService.getUsers();
        return response;
    }
}