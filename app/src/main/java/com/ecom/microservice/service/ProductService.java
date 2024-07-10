package com.ecom.microservice.service;

import java.util.List;

import com.ecom.microservice.api.model.ProductResponse;

/**
 * Product service.
 */
public interface ProductService {

    /**
     * Finds all products in the database.
     *
     * @return product records from the database
     *
     * @see ProductResponse
     */
    List<ProductResponse> searchAll();
}
