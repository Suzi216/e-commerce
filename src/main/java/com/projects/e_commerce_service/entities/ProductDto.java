package com.projects.e_commerce_service.entities;

import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.UUID;

@Data
public class ProductDto {
    private UUID id;
    private String name;

    private String description;
    private Double unitPrice;
    private String img;
    private List<CategoryType> categories;
    private Integer variability;
    private String characteristics;


    public static ProductDto builFrom(Product product) {
        return new ModelMapper().map(product, ProductDto.class);
    }
}
