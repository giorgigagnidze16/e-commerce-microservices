package com.ecom.microservice.web;

import java.util.List;

import com.ecom.microservice.api.model.CreateProductRequest;
import com.ecom.microservice.api.model.ProductResponse;
import com.ecom.microservice.service.ProductService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public List<ProductResponse> search(
        @Parameter(description = "Page Index") @RequestParam(value = "i", defaultValue = "0") int page,
        @Parameter(description = "Page Size") @RequestParam(value = "s", defaultValue = "20") int size) {
        return productService.search(PageRequest.of(page, size));
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


    @Operation(summary = "Archive product", description = "Set product visibility, hide/show")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Updated successfully"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @PatchMapping("/{id}/")
    public void archive(@PathVariable Long id, @RequestParam boolean archived) {
        log.debug("Received archive request, archived: {}", archived);
        productService.updateProductVisibility(id, archived);
    }
}
