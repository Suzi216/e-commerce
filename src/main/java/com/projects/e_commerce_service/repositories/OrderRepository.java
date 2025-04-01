package com.projects.e_commerce_service.repositories;

import com.projects.e_commerce_service.dtos.OrderDto;
import com.projects.e_commerce_service.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query(value = "SELECT new com.projects.e_commerce_service.dtos.OrderDto(o.id,o.orderStatus, o.customerId, o.adress, o.ordered,o.shipped, o.total) FROM Order o WHERE o.customerId = :customerId")
    List<OrderDto> findAllByCustomerId( UUID customerId);
}
