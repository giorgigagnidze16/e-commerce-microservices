package com.ecom.microservice.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.ecom.microservice.api.model.CreateProductRequest;
import com.ecom.microservice.api.model.ImageResponse;
import com.ecom.microservice.api.model.ProductResponse;
import com.ecom.microservice.entity.Image;
import com.ecom.microservice.entity.Product;
import com.ecom.microservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Product service.
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ImageService imageService;

    /**
     * Finds all products in the database.
     *
     * @return product records from the database
     * @see ProductResponse
     */
    public List<ProductResponse> search(PageRequest pageRequest) {
        return repository.findAll(pageRequest).stream().map(ProductService::mapToResponse).toList();
    }

    /**
     * Create a product listing
     *
     * @param request details of listing
     * @return ProductResponse optional
     * @throws Exception if image upload failed, or jpa error occurs
     */
    public Optional<ProductResponse> create(CreateProductRequest request) throws Exception {
        var product = new Product(request.title(), request.description(), request.price(),
            request.discount(), request.stock(), request.archived());

        Set<Image> images = new HashSet<>();
        for (MultipartFile file : request.files()) {
            String url = imageService.uploadAndGetUrl(file);
            images.add(new Image(product, url));
        }

        product.setImages(images);

        return Optional.of(repository.save(product)).map(ProductService::mapToResponse);
    }

    private static ProductResponse mapToResponse(Product product) {
        var attachments = product.getImages().stream()
            .map(img -> new ImageResponse(img.getId(), img.getUrl()))
            .toList();

        return ProductResponse.builder()
            .id(product.getId())
            .stock(product.getStock())
            .price(product.getPrice())
            .discount(product.getDiscount())
            .archived(product.getArchived())
            .title(product.getTitle())
            .description(product.getDescription())
            .attachments(attachments)
            .createdAt(product.getCreatedAt())
            .updatedAt(product.getUpdatedAt())
            .build();
    }
}
