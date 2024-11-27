package com.projetobackEnd.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetobackEnd.model.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    

    
}
