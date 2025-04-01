package com.projects.e_commerce_service.dtos;

import com.projects.e_commerce_service.entities.OrderStatus;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class OrderRequestDto {

    private OrderStatus orderStatus;
    private String adress;
    private UUID customerId;
    private  Integer total;
}
