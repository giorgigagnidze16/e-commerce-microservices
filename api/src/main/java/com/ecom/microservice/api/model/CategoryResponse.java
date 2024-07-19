package com.ecom.microservice.api.model;

/**
 * Category response dto.
 *
 * @param id of category
 * @param name of category
 */
public record CategoryResponse(Long id, String name) {
}
