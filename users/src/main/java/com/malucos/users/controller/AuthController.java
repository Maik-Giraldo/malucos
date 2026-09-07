package com.malucos.users.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malucos.users.dto.HttpGlobalResponseDTO;
import com.malucos.users.dto.LoginRequestDTO;
import com.malucos.users.dto.LoginResponseDTO;
import com.malucos.users.dto.RegisterRequestDTO;
import com.malucos.users.dto.UserDTO;
import com.malucos.users.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthController {

    /**
     * Servicio de auth
     */
    private final AuthService authService;

    /**
     * Método para registrar un usuario
     * 
     * @param request
     * @return
     */
    @PostMapping("register")
    public ResponseEntity<HttpGlobalResponseDTO<UserDTO>> register(@RequestBody RegisterRequestDTO request) {
        try {
            HttpGlobalResponseDTO<UserDTO> response = authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("login")
    public ResponseEntity<HttpGlobalResponseDTO<LoginResponseDTO>> login(@RequestBody LoginRequestDTO request) {
        try {
            HttpGlobalResponseDTO<LoginResponseDTO> response = authService.login(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
