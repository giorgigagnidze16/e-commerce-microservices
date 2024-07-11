package com.ecom.microservice.web;

import java.util.List;

import com.ecom.microservice.api.model.CreateProductRequest;
import com.ecom.microservice.api.model.ProductResponse;
import com.ecom.microservice.service.ProductService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@AllArgsConstructor
@Validated
@Tag(name = "Product")
@OpenAPIDefinition(tags = @Tag(name = "Product", description = "Products controller"))
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Operation(
        summary = "Query all products",
        description = "Query the db to retrieve all available products"
    )
    @GetMapping
    public List<ProductResponse> searchAll() {
        return productService.searchAll();
    }

    @Operation(
        summary = "Create new product listing",
        description = "Inserts a new product into the database"
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponse> create(
        @ModelAttribute CreateProductRequest request
    ) throws Exception {
        log.debug("Received product creation request {}", request);
        return ResponseEntity.of(productService.create(request));
    }
}
