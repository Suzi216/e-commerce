package com.projects.e_commerce_service.services;

import com.projects.e_commerce_service.dtos.OrderDto;
import com.projects.e_commerce_service.dtos.OrderRequestDto;
import com.projects.e_commerce_service.entities.Order;
import com.projects.e_commerce_service.entities.OrderStatus;
import com.projects.e_commerce_service.entities.Product;
import com.projects.e_commerce_service.entities.StatusUpdateDto;
import com.projects.e_commerce_service.exceptions.ResourceNotFoundException;
import com.projects.e_commerce_service.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {
    final private OrderRepository orderRepository;

    public OrderDto createOrder(OrderRequestDto payload) {

        Order order =new Order();
        order.setCreatedAt(new Date());
        order.setCreatedBy(payload.getCustomerId());
        order.setOrderStatus(payload.getOrderStatus());
        order.setCustomerId(payload.getCustomerId());
        order.setAdress(payload.getAdress());
        order.setTotal(payload.getTotal());
        order=orderRepository.save(order);
        return new ModelMapper().map(order, OrderDto.class);
    }

    public void cancelOrder(UUID orderId) throws ResourceNotFoundException {
        Order order= orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        if (order.getOrderStatus().equals(OrderStatus.PROCESSING)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot cancel processed orders");

        }
        order.setOrderStatus(OrderStatus.CANCEL);
        orderRepository.save(order);

    }


    public List<OrderDto> getmyOrders(UUID customerId) {
        return orderRepository.findAllByCustomerId(customerId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderDto updateOrderStatus(UUID orderId, StatusUpdateDto payload) throws ResourceNotFoundException {
        System.out.println(payload.getOrderStatus());
        Order order= orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        order.setOrderStatus(payload.getOrderStatus());
        return new ModelMapper().map(orderRepository.save(order),OrderDto.class) ;
    }
}
