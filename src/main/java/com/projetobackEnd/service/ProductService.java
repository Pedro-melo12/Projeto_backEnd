package com.projetobackEnd.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetobackEnd.model.Product;
import com.projetobackEnd.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Método para buscar um produto por ID
    public Optional<Product> getProductById(UUID id) {
        return productRepository.findById(id);
    }

    // Método para buscar todos os produtos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Método para atualizar um produto
    public Product updateProduct(UUID id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(existingProduct -> {existingProduct.setName(updatedProduct.getName());
                    existingProduct.setDescription(updatedProduct.getDescription());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));
    }

    // Método para deletar um produto por ID
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}


