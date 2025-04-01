package com.projects.e_commerce_service.dtos;

import com.projects.e_commerce_service.entities.ProductDto;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ShoppingCartDto {

    private UUID customerId;
    private List<ProductDto> products;
}
