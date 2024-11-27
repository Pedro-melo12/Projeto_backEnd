package com.projetobackEnd.service;

import com.projetobackEnd.dto.ProductDTO;
import com.projetobackEnd.model.Order;
import com.projetobackEnd.model.Product;
import com.projetobackEnd.model.User;
import com.projetobackEnd.repository.OrderRepository;
import com.projetobackEnd.repository.ProductRepository;
import com.projetobackEnd.repository.UserRepository;
import com.projetobackEnd.dto.OrderDTO;


import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository; // Repositório do usuário

    @Autowired 
    private ProductRepository productRepository;
   

   

    
     
    @Transactional
public Order createOrder(OrderDTO orderDTO) {
    // Obter o nome de usuário do usuário logado
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = userDetails.getUsername();

    // Buscar o usuário no banco de dados com base no nome de usuário
    Optional<User> userOptional = userRepository.findByUsernameIgnoreCase(username);
    if (userOptional.isPresent()) {
        User user = userOptional.get();
        // Criação do pedido e associação com o usuário logado
        Order order = new Order();
        order.setUser(user);

        // Criar os produtos e adicionar ao pedido
        List<Product> products = new ArrayList<>();
        for (ProductDTO productDTO : orderDTO.getProducts()) {
            // Buscar o produto existente pelo ID no banco de dados
            Optional<Product> existingProduct = productRepository.findById(productDTO.id());
            if (existingProduct.isPresent()) {
                products.add(existingProduct.get());
            } else {
                throw new RuntimeException("Produto não encontrado com ID: " + productDTO.id());
            }
        }
        order.setProducts(products);

        // Calcular o preço total
        Double totalPrice = products.stream().mapToDouble(Product::getPrice).sum();
        order.setTotalPrice(totalPrice);

        // Salvar o pedido
        return orderRepository.save(order);
    } else {
        throw new RuntimeException("Usuário não encontrado");
    }
}

    
    
        

    

    // Deletar um pedido por ID
    public boolean deleteOrder(UUID orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            orderRepository.delete(order.get());
            return true;
        }
        return false;
    }

     // Atualizar um pedido
     public Order updateOrder(UUID orderId, Order updatedOrder) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order existingOrder = orderOptional.get();
            existingOrder.setProducts(updatedOrder.getProducts());

            // Calcular o preço total do pedido atualizado
            Double totalPrice = 0.0;
            for (Product product : updatedOrder.getProducts()) {
                totalPrice += product.getPrice();
            }
            existingOrder.setTotalPrice(totalPrice); // Atualizar o preço total

            return orderRepository.save(existingOrder);
        }
        return null; // Pedido não encontrado
    }

    // Obter um pedido por ID
    public Optional<Order> getOrderById(UUID orderId) {
        return orderRepository.findById(orderId);
    }

    // Obter todos os pedidos
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
