package com.projetobackEnd.model;

import java.util.List;
import java.util.UUID;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JsonManagedReference  // Evita a serialização do usuário dentro de orders
    private User user;

    @ManyToMany
    @JsonManagedReference  // A relação de produtos gerenciados em orders
    private List<Product> products;

    @Column(name = "totalPrice", nullable = false)
    private Double totalPrice;
}
