package com.projetobackEnd.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetobackEnd.model.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {

}
