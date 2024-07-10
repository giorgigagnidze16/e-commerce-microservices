package com.ecom.microservice.web;

import java.util.List;

import com.ecom.microservice.api.model.ProductResponse;
import com.ecom.microservice.service.ProductService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Product")
@OpenAPIDefinition(tags = @Tag(name = "Product", description = "Products controller"))
public class ProductController {
    private final ProductService productService;

    @Operation(
        summary = "Query all products",
        description = "Query the db to retrieve all available products")
    @GetMapping
    public List<ProductResponse> searchAll() {
        return productService.searchAll();
    }
}
