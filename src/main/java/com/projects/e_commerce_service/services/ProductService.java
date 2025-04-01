package com.projects.e_commerce_service.services;

import com.projects.e_commerce_service.dtos.ProductUpdateRequestDto;
import com.projects.e_commerce_service.entities.CategoryType;
import com.projects.e_commerce_service.entities.Product;
import com.projects.e_commerce_service.entities.ProductDto;
import com.projects.e_commerce_service.entities.ProductRequestDto;
import com.projects.e_commerce_service.exceptions.ResourceNotFoundException;
import com.projects.e_commerce_service.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public ProductDto createProduct(ProductRequestDto payload) {

        Product product= new ModelMapper().map(payload, Product.class);
        product.setCreatedAt(new Date());
        //TODO
        product.setCreatedBy(UUID.randomUUID());
        product=productRepository.save(product);
        return new ModelMapper().map(product, ProductDto.class);

    }

    public ProductDto updateProduct(ProductUpdateRequestDto payload, UUID id) throws ResourceNotFoundException {
        Product product= productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        if (payload.getSize() != null) {
            product.setSize(payload.getSize());
        }
        if (payload.getName() != null) {
            product.setName(payload.getName());
        }
        if (payload.getImg() != null) {
            product.setImg(payload.getImg());
        }
        if (payload.getRating() != null) {
            product.setRating(payload.getRating());
        }
        if (payload.getCategory() != null) {
            List<CategoryType> categories=product.getCategories();
            categories.add(payload.getCategory());
            product.setCategories(categories);
        }
        if (payload.getDescription() != null) {
            product.setDescription(payload.getDescription());
        }
        if (payload.getUnitPrice() != null) {
            product.setUnitPrice(payload.getUnitPrice());
        }
        if (payload.getVariability() != null) {
            product.setVariability(payload.getVariability());
        }

        return ProductDto.builFrom(productRepository.save(product));
    }

    public void deleteProduct(UUID id) throws ResourceNotFoundException {
        Product product= productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        product.setDeletedAt(new Date());
        productRepository.save(product);
    }

    public Page<ProductDto> findAllProducts(Pageable pageable) {
        Page<Product> products=productRepository.findAll(pageable);

        return products.map(ProductDto::builFrom);

    }
}
