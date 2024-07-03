package com.ecom.microservice.data.service;

import java.util.List;

import com.ecom.microservice.data.model.Item;
import com.ecom.microservice.data.repository.ItemRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ItemServiceImpl implements ItemService {
    private final ItemRepository repository;

    @Override
    public List<Item> searchAll() {
        return repository.findAll();
    }
}
