package com.ecom.microservice.data.web;

import java.util.List;

import com.ecom.microservice.data.model.Item;
import com.ecom.microservice.data.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public List<Item> searchAll() {
        return itemService.searchAll();
    }
}
