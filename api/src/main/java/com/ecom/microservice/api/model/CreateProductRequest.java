package com.ecom.microservice.api.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

/**
 * Create product listing DTO.
 *
 * @param title of listing
 * @param description of product
 * @param price of product
 * @param discount amount to be applied to the original price
 * @param stock inventory capacity
 * @param archived if listing is archived or not
 * @param files image attachments
 */
public record CreateProductRequest(@NotBlank @Size(min = 3, max = 400) String title,
                                   @NotBlank @Size(min = 50, max = 760) String description,
                                   @NotNull @DecimalMin(value = "0.0", inclusive = false) BigDecimal price,
                                   @NotNull @DecimalMin(value = "0.0") BigDecimal discount,
                                   @NotNull @Min(1) Long stock, @NotNull Boolean archived,
                                   @NotEmpty MultipartFile[] files) {
}
