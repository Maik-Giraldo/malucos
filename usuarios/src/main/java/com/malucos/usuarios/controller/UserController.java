package com.malucos.usuarios.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malucos.usuarios.dto.HttpGlobalResponse;
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
    public ResponseEntity<MessageResponseDTO> createUser(@RequestBody RegisterRequestDTO request) {
        MessageResponseDTO response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get-users")
    public List<UserResponseDTO> getUsers() {
        List<UserResponseDTO> response = userService.getUsers();
        return response;
    }

    @GetMapping("/get-user/{id}")
    public HttpGlobalResponse<UserResponseDTO> getUser(@PathVariable Integer id) {
        HttpGlobalResponse<UserResponseDTO> response = userService.getUser(id);
        return response;
    }

    @DeleteMapping("/delete-user/{id}")
    public MessageResponseDTO deleteUser(@PathVariable Long id) {
        MessageResponseDTO response = userService.deleteUser(id);
        return response;
    }

    @PutMapping("/update-user/{id}")
    public HttpGlobalResponse<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody RegisterRequestDTO request) {
        HttpGlobalResponse<UserResponseDTO> response = userService.updateUser(id, request);
        return response;
    }
}