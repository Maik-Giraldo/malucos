package com.malucos.users.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malucos.users.dto.HttpGlobalResponseDTO;
import com.malucos.users.dto.UserDTO;
import com.malucos.users.enums.RoleEnum;
import com.malucos.users.security.RequiresRole;
import com.malucos.users.service.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/get-users")
    @RequiresRole(RoleEnum.ADMIN)
    public ResponseEntity<HttpGlobalResponseDTO<List<UserDTO>>> getUsers() {
        try {
            HttpGlobalResponseDTO<List<UserDTO>> response = usersService.getUsers();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
