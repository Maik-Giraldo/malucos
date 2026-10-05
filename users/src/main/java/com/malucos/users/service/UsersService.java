package com.malucos.users.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.malucos.users.dto.HttpGlobalResponseDTO;
import com.malucos.users.dto.UserDTO;
import com.malucos.users.entity.Users;
import com.malucos.users.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UsersService {

    private final UsersRepository usersRepository;
    
    public HttpGlobalResponseDTO<List<UserDTO>> getUsers() {
        HttpGlobalResponseDTO<List<UserDTO>> response = new HttpGlobalResponseDTO<>();
        List<UserDTO> listUsers = new ArrayList<>();

        List<Users> usersFound = usersRepository.findAll();

        for (Users u : usersFound) {
            UserDTO user = new UserDTO();
            user.setId(u.getId());
            user.setUsername(u.getUsername());
            user.setEmail(u.getEmail());
            user.setRolId(u.getRoles().getName());

            listUsers.add(user);
        }

        response.setMessage("Usuarios encontrados");
        response.setData(listUsers);

        return response;
    }
}
