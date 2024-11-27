package com.projetobackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.projetobackEnd.model.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {


    UserDetails findByUsername(String username);

    
    Optional<User> findByUsernameIgnoreCase(String username);
}
