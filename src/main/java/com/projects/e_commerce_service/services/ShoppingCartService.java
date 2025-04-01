package com.projects.e_commerce_service.services;

import com.projects.e_commerce_service.dtos.ProductCartDto;
import com.projects.e_commerce_service.dtos.ShoppingCartDto;
import com.projects.e_commerce_service.entities.Product;
import com.projects.e_commerce_service.entities.ShoppingCart;
import com.projects.e_commerce_service.exceptions.ResourceNotFoundException;
import com.projects.e_commerce_service.repositories.ProductRepository;
import com.projects.e_commerce_service.repositories.ShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.matcher.StringMatcher;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShoppingCartService {
    final private ShoppingCartRepository shoppingCartRepository;
    final private ProductRepository productRepository;

    public ShoppingCartDto getCartByUserId(UUID userId) {

        ShoppingCart cart = shoppingCartRepository.findByCustomerId(userId)
                .orElseGet(() -> createNewCart(userId));
        return new ModelMapper().map(cart, ShoppingCartDto.class);
    }

    private ShoppingCart createNewCart(UUID userId) {
        ShoppingCart cart = new ShoppingCart();
        cart.setCustomerId(userId);
        cart.setCreatedBy(userId);
        cart.setCreatedAt(new Date());
        return shoppingCartRepository.save(cart);
    }

    public ShoppingCartDto addProductToCart(UUID productId, UUID customerId, int quantity) throws ResourceNotFoundException {
        ShoppingCart shoppingCart = shoppingCartRepository.findByCustomerId(customerId).orElseThrow(() -> new ResourceNotFoundException("Shopping Cart not found"));
        List<Product> products = shoppingCart.getProducts();
        Optional<Product> product = products.stream().filter(item -> item.getCustomerId().equals(customerId)).findFirst();
        if (product.isPresent()) {
            product.get().setVariability(product.get().getVariability() + quantity);

        } else {
            Product newproduct = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            newproduct.setVariability(quantity);
            newproduct.setCustomerId(customerId);
            newproduct.setShoppingCardId(shoppingCart.getId());
            shoppingCart.getProducts().add(newproduct);
        }
        shoppingCartRepository.save(shoppingCart);
        return new ModelMapper().map(shoppingCart, ShoppingCartDto.class);
    }

    public ShoppingCartDto removeProductFromCart(UUID productId, UUID customerId) throws ResourceNotFoundException {
        ShoppingCart shoppingCart = shoppingCartRepository.findByCustomerId(customerId).orElseThrow(() -> new ResourceNotFoundException("Shopping Cart not found"));
        shoppingCart.getProducts().removeIf(item -> item.getId().equals(productId));

        shoppingCartRepository.save(shoppingCart);

        return new ModelMapper().map(shoppingCart, ShoppingCartDto.class);

    }

    public void clearCart(UUID customerId) throws ResourceNotFoundException {
        ShoppingCart shoppingCart = shoppingCartRepository.findByCustomerId(customerId).orElseThrow(() -> new ResourceNotFoundException("Shopping Cart not found"));
        shoppingCart.getProducts().clear();
        shoppingCartRepository.save(shoppingCart);

    }

    public List<ProductCartDto> getCartProducts(UUID customerId) throws ResourceNotFoundException {
        ShoppingCart shoppingCart = shoppingCartRepository.findByCustomerId(customerId).orElseThrow(() -> new ResourceNotFoundException("Shopping Cart not found"));
        ProductCartDto productCartDto= new ProductCartDto();
        List<ProductCartDto> productCartDtos =shoppingCart.getProducts().stream().map(product -> {
            productCartDto.setName(product.getName());
            productCartDto.setImg(product.getImg());
            productCartDto.setUnitPrice(product.getUnitPrice());
            productCartDto.setVariability(product.getVariability());
            return productCartDto;
        }).collect(Collectors.toList());

        return productCartDtos;
    }
}