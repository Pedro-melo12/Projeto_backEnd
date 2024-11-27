package com.projetobackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.projetobackEnd.model.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    // Retorna um UserDetails para autenticação (continua funcionando como está)
    UserDetails findByUsername(String username);

    // Método adicional para obter um User comum
    Optional<User> findByUsernameIgnoreCase(String username);
}
