package com.projects.e_commerce_service.repositories;

import com.projects.e_commerce_service.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
