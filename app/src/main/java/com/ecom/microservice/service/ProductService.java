package com.ecom.microservice.service;

import java.util.List;

import com.ecom.microservice.api.model.ProductResponse;
import com.ecom.microservice.entity.Product;
import com.ecom.microservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    /**
     * Finds all products in the database.
     *
     * @return product records from the database
     * @see ProductResponse
     */
    public List<ProductResponse> searchAll() {
        return repository.findAll().stream().map(ProductService::mapToResponse).toList();
    }

    private static ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
            .id(product.getId())
            .stock(product.getStock())
            .archived(product.getArchived())
            .title(product.getTitle())
            .description(product.getDescription())
            .createdAt(product.getCreatedAt())
            .updatedAt(product.getUpdatedAt())
            .build();
    }
}
