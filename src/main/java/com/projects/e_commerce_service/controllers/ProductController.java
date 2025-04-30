package com.projects.e_commerce_service.controllers;

import com.projects.e_commerce_service.dtos.ProductUpdateRequestDto;
import com.projects.e_commerce_service.entities.ProductDto;
import com.projects.e_commerce_service.entities.ProductRequestDto;
import com.projects.e_commerce_service.exceptions.ResourceNotFoundException;
import com.projects.e_commerce_service.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000" })

public class ProductController {
    private final ProductService productService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<ProductDto> createProduct(@ModelAttribute @Valid ProductRequestDto payload) {
        try {
            ProductDto productDto=productService.createProduct(payload);
            return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
        }
        catch (Exception e){
            return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage())).build();

        }
    }
    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<ProductDto> updateProduct(@ModelAttribute ProductUpdateRequestDto payload, @PathVariable UUID id) {
        try {
            ProductDto productDto=productService.updateProduct(payload,id);
            return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
        }
        catch (Exception e){
            return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage())).build();

        }
    }
//    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable UUID id) throws ResourceNotFoundException {
        productService.deleteProduct(id);

    }
    @GetMapping
    public ResponseEntity<Page<ProductDto>> findAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        try {
            Pageable pageable= PageRequest.of(page,size);
            return ResponseEntity.ok(productService.findAllProducts(pageable));
        }catch (Exception e){
            return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage())).build();
        }
    }
}
