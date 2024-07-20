package com.ecom.microservice.api.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;

/**
 * Price range dto
 *
 * @param min minimal price of products
 * @param max maximal price of products.
 */
public record PriceRange(@DecimalMin("0.0") BigDecimal min,
                         @DecimalMin("0.01") BigDecimal max) {
}
