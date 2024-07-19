package com.ecom.microservice.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;

/**
 * Product DTO
 *
 * @param id           of product
 * @param stock        total inventory available
 * @param archived     determines if a listing is archived or not
 * @param title        of product listing
 * @param description  of product
 * @param price        of product
 * @param discount     applied to the original price of a product
 * @param attachments  urls of listing attachments
 * @param manufacturer product manufacturer
 * @param createdAt    listing creation timestamp
 * @param updatedAt    listing update timestamp
 */
@Builder
public record ProductResponse(Long id, Long stock,
                              Boolean archived, String title,
                              String description, BigDecimal price,
                              BigDecimal discount, List<ImageResponse> attachments,
                              ManufacturerResponse manufacturer, List<CategoryResponse> categories,
                              LocalDateTime createdAt, LocalDateTime updatedAt) {
}
