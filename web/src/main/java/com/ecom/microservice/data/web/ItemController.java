package com.ecom.microservice.data.web;

import java.util.List;

import com.ecom.microservice.data.model.Item;
import com.ecom.microservice.data.service.ItemService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Item")
@OpenAPIDefinition(tags = @Tag(name = "Item", description = "Items controller"))
public class ItemController {
    private final ItemService itemService;

    @Operation(
        summary = "Query all items",
        description = "Query the db to retrieve all available items")
    @GetMapping
    public List<Item> searchAll() {
        return itemService.searchAll();
    }
}
