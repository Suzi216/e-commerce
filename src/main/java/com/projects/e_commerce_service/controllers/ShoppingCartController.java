package com.projects.e_commerce_service.controllers;

import com.projects.e_commerce_service.dtos.ProductCartDto;
import com.projects.e_commerce_service.dtos.ShoppingCartDto;
import com.projects.e_commerce_service.entities.Product;
import com.projects.e_commerce_service.entities.ProductDto;
import com.projects.e_commerce_service.exceptions.ResourceNotFoundException;
import com.projects.e_commerce_service.services.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000" })
public class ShoppingCartController {
    final private ShoppingCartService shoppingCartService;

    @GetMapping("/{userId}")
    public ResponseEntity<ShoppingCartDto> getCart(@PathVariable UUID userId) {
        return ResponseEntity.ok(shoppingCartService.getCartByUserId(userId));
    }

    @PostMapping("/add")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ShoppingCartDto>  addProductToCart(@RequestParam UUID productId, @RequestParam UUID customerId,
                                                       @RequestParam int quantity){
        try {
            ShoppingCartDto shoppingCartDto=shoppingCartService.addProductToCart(productId,customerId,quantity);
            return ResponseEntity.ok(shoppingCartDto);
        }catch (Exception e){
            return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage())).build();
        }
    }
    @DeleteMapping("/remove")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ShoppingCartDto> removeProductFromCart(@RequestParam UUID productId, @RequestParam UUID customerId){
        try {
            ShoppingCartDto shoppingCartDto=shoppingCartService.removeProductFromCart(productId,customerId);
            return ResponseEntity.ok(shoppingCartDto);
        }catch (Exception e){
            return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage())).build();
        }
    }
    @GetMapping("/{customerId}/products")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ProductCartDto>> getCartProducts(@PathVariable UUID customerId){
        try {
            List<ProductCartDto> products=shoppingCartService.getCartProducts(customerId);
            return ResponseEntity.ok(products);
        }catch (Exception e){
            return  ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage())).build();
        }

    }

    @DeleteMapping("/clear/{customerId}")
//    @PreAuthorize("hasRole('USER')")
    public  void clearCart(@PathVariable UUID customerId) throws ResourceNotFoundException {
            shoppingCartService.clearCart(customerId);

    }


}
