package com.projects.e_commerce_service.dtos;

import com.projects.e_commerce_service.entities.CategoryType;
import lombok.Data;

import java.util.List;

@Data
public class ProductUpdateRequestDto {
    private String name, description, size;
    private Double unitPrice;
    private String img;
    private Integer variability, rating;
    private CategoryType category;

}
