package com.malucos.usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.malucos.usuarios.entity.Users;

@Repository // Crea un bean (una instancia)
public interface UserRepository extends JpaRepository<Users, Long>{
    Optional<Users> findByEmail(String email);
}