package com.projects.e_commerce_service.dtos;

import com.projects.e_commerce_service.entities.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
public class OrderDto {
    private UUID id;
    private OrderStatus orderStatus;
    private UUID customerId;
    private String adress;
    private Date ordered;
    private Date shipped;
    private Integer total;
    public OrderDto(){

    }
    public OrderDto(UUID id, OrderStatus orderStatus, UUID customerId, String adress, Date ordered, Date shipped, Integer total) {
        this.id=id;
        this.orderStatus=orderStatus;
        this.customerId=customerId;
        this.adress=adress;
        this.ordered=ordered;
        this.shipped=shipped;
        this.total=total;
    }


}
