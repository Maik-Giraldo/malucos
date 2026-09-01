package com.malucos.users.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.malucos.users.config.AppConfig;
import com.malucos.users.dto.HttpGlobalResponseDTO;
import com.malucos.users.dto.RegisterRequestDTO;
import com.malucos.users.dto.UserDTO;
import com.malucos.users.entity.Roles;
import com.malucos.users.entity.Users;
import com.malucos.users.repository.RolesRepository;
import com.malucos.users.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    /**
     * Repositorio de usuarios
     */
    private final UsersRepository usersRepository;

    /**
     * Repositorio de roles
     */
    private final RolesRepository rolesRepository;

    /**
     * Encriptador de contraseñas
     */
    private final PasswordEncoder passwordEncoder;

    public HttpGlobalResponseDTO<UserDTO> register(RegisterRequestDTO request) {
        HttpGlobalResponseDTO<UserDTO> response = new HttpGlobalResponseDTO<>();
        UserDTO userDTO = new UserDTO();

        Optional<Users> usernameFound = usersRepository.findByUsername(request.getUsername());
        Optional<Users> emailFound = usersRepository.findByEmail(request.getEmail());
        Optional<Roles> rol = rolesRepository.findById(request.getRolId());

        if (usernameFound.isPresent()) {
            response.setMessage("El nombre de usuario ya está en uso");
            return response;
        }

        if (emailFound.isPresent()) {
            response.setMessage("El correo de usuario ya está en uso");
            return response;
        }

        if (rol.isEmpty()) {
            response.setMessage("El rol no existe");
            return response;
        }

        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRolId(rol.get());

        usersRepository.save(user);

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRolId(rol.get().getName());

        response.setMessage("Usuario registrado correctamente");
        response.setData(userDTO);

        return response;
    }
}
