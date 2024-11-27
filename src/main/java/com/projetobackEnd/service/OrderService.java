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
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Order createOrder(OrderDTO orderDTO) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        Optional<User> userOptional = userRepository.findByUsernameIgnoreCase(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            Order order = new Order();
            order.setUser(user);

            List<Product> products = new ArrayList<>();
            for (ProductDTO productDTO : orderDTO.getProducts()) {

                Optional<Product> existingProduct = productRepository.findById(productDTO.id());
                if (existingProduct.isPresent()) {
                    products.add(existingProduct.get());
                } else {
                    throw new RuntimeException("Produto não encontrado com ID: " + productDTO.id());
                }
            }
            order.setProducts(products);

            Double totalPrice = products.stream().mapToDouble(Product::getPrice).sum();
            order.setTotalPrice(totalPrice);

            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    public boolean deleteOrder(UUID orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            orderRepository.delete(order.get());
            return true;
        }
        return false;
    }

    // Não ta funcionando
    public Order updateOrder(UUID orderId, Order updatedOrder) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order existingOrder = orderOptional.get();
            existingOrder.setProducts(updatedOrder.getProducts());

            Double totalPrice = 0.0;
            for (Product product : updatedOrder.getProducts()) {
                totalPrice += product.getPrice();
            }
            existingOrder.setTotalPrice(totalPrice);

            return orderRepository.save(existingOrder);
        }
        return null;
    }

    public Optional<Order> getOrderById(UUID orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
