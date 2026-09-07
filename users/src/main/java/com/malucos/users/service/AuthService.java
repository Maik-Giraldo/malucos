package com.malucos.users.service;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.malucos.users.config.AppConfig;
import com.malucos.users.dto.HttpGlobalResponseDTO;
import com.malucos.users.dto.LoginRequestDTO;
import com.malucos.users.dto.LoginResponseDTO;
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

    /**
     * Servicio de jwt
     */
    private final JwtService jwtService;

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

    public HttpGlobalResponseDTO<LoginResponseDTO> login(LoginRequestDTO request) {
        HttpGlobalResponseDTO<LoginResponseDTO> response = new HttpGlobalResponseDTO<>();
        Optional<Users> email = usersRepository.findByEmail(request.getUser());
        Optional<Users> username = usersRepository.findByUsername(request.getUser());

        if (username.isEmpty() && email.isEmpty()){
            response.setMessage("Usuario y/o contraseña incorrectos");
            return response;
        }

        Users userFound;

        if (email.isPresent()) {
            userFound = email.get();
        } else {
            userFound = username.get();
        }
        
        if (!passwordEncoder.matches(request.getPassword(), userFound.getPassword())) {
            response.setMessage("Usuario y/o contraseña incorrectos");
            return response;
        }

        String jwt = jwtService.generateToken(userFound.getId(), userFound.getUsername(), userFound.getRolId());
        LoginResponseDTO token = new LoginResponseDTO();
        token.setJwt(jwt);
        response.setMessage("Inicio de sesión exitoso");
        response.setData(token);

        return response;
    }
}
