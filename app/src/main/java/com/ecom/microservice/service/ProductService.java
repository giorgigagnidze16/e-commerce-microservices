package com.ecom.microservice.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.ecom.microservice.api.exception.ResourceNotFoundException;
import com.ecom.microservice.api.model.CategoryResponse;
import com.ecom.microservice.api.model.CreateProductRequest;
import com.ecom.microservice.api.model.ImageResponse;
import com.ecom.microservice.api.model.ManufacturerResponse;
import com.ecom.microservice.api.model.PriceRange;
import com.ecom.microservice.api.model.ProductResponse;
import com.ecom.microservice.entity.Image;
import com.ecom.microservice.entity.Product;
import com.ecom.microservice.repository.ProductRepository;
import com.ecom.microservice.web.validation.annotation.ValidPriceRange;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

/**
 * Product service.
 */
@Slf4j
@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ImageService imageService;
    private final CategoriesService categoriesService;
    private final ManufacturerService manufacturerService;


    /**
     * Finds products in the database according to a search query
     * with a full text search in product title and description.
     *
     * @param query        search query
     * @param manufacturer of product
     * @param categories   of product
     * @param range        price range
     * @return product records from the database
     * @see ProductResponse
     */
    public List<ProductResponse> search(
        @NotNull String query,
        @Nullable Long manufacturer,
        @NotNull List<Long> categories,
        @ValidPriceRange PriceRange range,
        @NotNull Pageable pageable
    ) {
        return productRepository.findByQuery(query, range.min(), range.max(), manufacturer, categories, pageable)
            .stream()
            .map(ProductService::mapToResponse)
            .toList();
    }

    /**
     * Create a product listing
     *
     * @param request details of listing
     * @return ProductResponse optional
     * @throws Exception if image upload failed, or jpa error occurs
     */
    public Optional<ProductResponse> create(CreateProductRequest request) throws Exception {
        var manufacturer = manufacturerService.findByName(request.manufacturer());

        var categories = categoriesService.findByIds(request.categories());

        if (categories.isEmpty()) {
            log.debug("Categories not found by given ids {}", request.categories());
            throw new ResourceNotFoundException("Categories not found by given ids: " + request.categories());
        }

        var product = new Product(request.title(), request.description(), request.price(),
            request.discount(), request.stock(), request.archived(), manufacturer);

        Set<Image> images = new HashSet<>();
        for (MultipartFile file : request.files()) {
            String url = imageService.uploadAndGetUrl(file);
            images.add(new Image(product, url));
        }

        product.setImages(images);
        product.setCategory(new HashSet<>(categories));

        return Optional.of(productRepository.save(product)).map(ProductService::mapToResponse);
    }

    /**
     * Updates product visibility by id.
     *
     * @param id       of product
     * @param archived true or false depending on if a person wants to hide/show product.
     */
    public void updateProductVisibility(Long id, boolean archived) {
        if (productRepository.updateVisibility(id, archived) <= 0) {
            log.warn("Product with id: {} does not exist", id);
            throw new ResourceNotFoundException("Couldn't find a product with id: " + id);
        }
    }

    /**
     * Updates product price by id.
     *
     * @param id    of product
     * @param price new price of a product.
     */
    public void updateProductPrice(@NotNull Long id, @NotNull final Double price) {
        if (productRepository.updatePrice(id, price) <= 0) {
            log.warn("Product with id: {} does not exist", id);
            throw new ResourceNotFoundException("Couldn't find a product with id: " + id);
        }
    }

    private static ProductResponse mapToResponse(Product product) {
        var attachments = product.getImages().stream()
            .map(img -> new ImageResponse(img.getId(), img.getUrl()))
            .toList();

        var categories = product.getCategory().stream()
            .map(category -> new CategoryResponse(category.getId(), category.getName()))
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
            .categories(categories)
            .manufacturer(
                new ManufacturerResponse(
                    product.getManufacturer().getId(),
                    product.getManufacturer().getName()
                )
            )
            .createdAt(product.getCreatedAt())
            .updatedAt(product.getUpdatedAt())
            .build();
    }
}
