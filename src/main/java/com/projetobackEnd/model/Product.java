package com.projetobackEnd.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "productName",nullable = false,length = 80)
    private String name;
    
    @Column(name = "description",nullable = false,length = 80)
    private String description;
    
    @Column(name = "price",nullable = false,length = 20)
    private int price;  

    @JsonBackReference
    @ManyToMany(mappedBy = "products")
    private List<Order> orders;


    public Product(String name, String description, int price) {
      this.name = name;
      this.description = description;
      this.price = price;
  }
}
