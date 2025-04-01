package com.projects.e_commerce_service.entities;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ProductRequestDto {
    private UUID id;
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Description is required")
    private String description;
    @NotNull(message = "Price  is required")
    private Double unitPrice;
    @NotNull(message = "Images are required")
    private String img;
    @NotNull(message = "Variability is required")
    private Integer variability;
    @NotNull(message = "Category  is required")
    private List<CategoryType> categories;
    @NotNull(message = "Characteristics are required")
    private String characteristics;
    @NotNull(message = "Size is required")
    private Integer size;

}
