package com.projects.e_commerce_service.repositories;

import com.projects.e_commerce_service.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, UUID> {
    Optional<ShoppingCart> findByCustomerId(UUID userId);
}
