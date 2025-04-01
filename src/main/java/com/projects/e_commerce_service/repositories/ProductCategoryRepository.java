package com.projects.e_commerce_service.repositories;

import com.projects.e_commerce_service.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {
}
