package com.malucos.usuarios.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.malucos.usuarios.dto.HttpGlobalResponse;
import com.malucos.usuarios.dto.MessageResponseDTO;
import com.malucos.usuarios.dto.RegisterRequestDTO;
import com.malucos.usuarios.dto.UserResponseDTO;
import com.malucos.usuarios.entity.Users;
import com.malucos.usuarios.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service // Crea un bean ( una sola instancia ) patrón de diseño Singleton
@RequiredArgsConstructor // Contructor de la clase
public class UserService {

    // Inyectamos el repositorio de usuarios
    private final UserRepository userRepository;

    public MessageResponseDTO createUser(RegisterRequestDTO request) {
        // Crea objeto vacio para setearle una respuesta
        MessageResponseDTO response = new MessageResponseDTO();
        response.setMessage("Usuario creado correctamente");

        // Crea una entidad vacia para guardarla en la base de datos
        Users user = new Users();
        user.setName(request.getName());
        user.setLastname(request.getLastName());
        user.setEmail(request.getEmail());
        user.setAge(request.getAge());
        userRepository.save(user);

        return response;
    }

    public List<UserResponseDTO> getUsers() {
        List<UserResponseDTO> listUsers = new ArrayList<>();
        List<Users> usersFound = userRepository.findAll();

        for (Users user : usersFound) {
            UserResponseDTO userNew = new UserResponseDTO();
            userNew.setId(user.getId());
            userNew.setName(user.getName());
            userNew.setLastName(user.getLastname());
            userNew.setAge(user.getAge());
            userNew.setEmail(user.getEmail());
            listUsers.add(userNew);
        }

        return listUsers;
    }

    public HttpGlobalResponse<UserResponseDTO> getUser(Integer id) {
        HttpGlobalResponse<UserResponseDTO> response = new HttpGlobalResponse<>();
        Optional<Users> userFound = userRepository.findById(id.longValue());

        if (userFound.isEmpty()) {
            response.setMessage("Usuario no encontrado");
            return response;
        }

        Users user = userFound.get();

        UserResponseDTO userFinal = new UserResponseDTO();
        userFinal.setMessage("Usuario encontrado");
        userFinal.setId(user.getId());
        userFinal.setName(user.getName());
        userFinal.setLastName(user.getLastname());
        userFinal.setEmail(user.getEmail());
        userFinal.setAge(user.getAge());

        response.setMessage("Usuario encontrado");
        response.setData(userFinal);

        return response;
    }
}