package com.projects.e_commerce_service.dtos;

import lombok.Data;

@Data
public class ProductCartDto {
    private String name;
    private String img;
    private Double unitPrice;
    private Integer variability;
}
