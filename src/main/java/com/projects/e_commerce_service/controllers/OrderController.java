package com.projects.e_commerce_service.controllers;

import com.projects.e_commerce_service.dtos.OrderDto;
import com.projects.e_commerce_service.dtos.OrderRequestDto;
import com.projects.e_commerce_service.entities.Order;
import com.projects.e_commerce_service.entities.StatusUpdateDto;
import com.projects.e_commerce_service.exceptions.ResourceNotFoundException;
import com.projects.e_commerce_service.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = {"http://localhost:8080"})

@RequiredArgsConstructor
public class OrderController {
    final private OrderService orderService;
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequestDto payload){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(payload));
    }
    @PutMapping("/{orderId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> cancelOrder(@PathVariable UUID orderId) throws ResourceNotFoundException {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Order cancelled");
    }
    @GetMapping("/me/{customerId}")

    //Todo  change customerID
    public ResponseEntity<List<OrderDto>> findMyOrders(@PathVariable UUID customerId){
        try {
            List<OrderDto> orders=orderService.getmyOrders(customerId);
            return ResponseEntity.ok(orders);
        }catch (Exception e){
            return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,e.getLocalizedMessage())).build();
        }
    }
    @GetMapping()
    public ResponseEntity<List<Order>> findOrders(){
        try {
            List<Order> orders=orderService.getAllOrders();
            return ResponseEntity.ok(orders);
        }catch (Exception e){
            return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,e.getLocalizedMessage())).build();
        }
    }
    @PatchMapping(value = "/{orderId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable UUID orderId,@ModelAttribute @Valid  StatusUpdateDto payload){
        try {
            OrderDto order=orderService.updateOrderStatus(orderId,payload);
            return ResponseEntity.ok(order);
        }catch (Exception e){
            return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,e.getLocalizedMessage())).build();
        }
    }
}
