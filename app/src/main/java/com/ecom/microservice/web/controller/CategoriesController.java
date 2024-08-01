package com.ecom.microservice.web.controller;


import com.ecom.microservice.api.model.CategoryResponse;
import com.ecom.microservice.service.CategoriesService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "Categories")
@OpenAPIDefinition(tags = @Tag(name = "Categories", description = "Categories controller"))
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoriesController {
    private final CategoriesService categoriesService;

    @Operation(
        summary = "Create new category",
        description = "Inserts a new category into the database"
    )
    @PostMapping
    public ResponseEntity<CategoryResponse> create(
        @RequestParam @Size(min = 10) String name
    ) {
        log.debug("Received category creation request {}", name);
        return ResponseEntity.of(categoriesService.create(name));
    }

}
