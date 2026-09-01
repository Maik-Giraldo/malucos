package com.malucos.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malucos.users.entity.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long>{
    
}
