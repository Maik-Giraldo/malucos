package com.malucos.usuarios.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malucos.usuarios.dto.MessageResponseDTO;
import com.malucos.usuarios.dto.RegisterRequestDTO;
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

        // Crea una entidad vacia para 
        Users user = new Users();
        user.setName(request.getName());
        user.setLastname(request.getLastName());
        user.setAge(request.getAge());
        userRepository.save(user);

        return response;
    }
}