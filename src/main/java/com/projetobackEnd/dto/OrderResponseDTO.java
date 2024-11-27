package com.projetobackEnd.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponseDTO {
    private UUID id;
    private String username; 
    private Double totalPrice;
    private List<ProductDTO> products;

}
