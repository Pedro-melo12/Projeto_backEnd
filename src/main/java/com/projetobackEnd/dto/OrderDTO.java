package com.projetobackEnd.dto;

import java.util.List;

public record OrderDTO(List<ProductDTO> products) {
  
    public List<ProductDTO> getProducts() {
            return products;
         }
    
}
