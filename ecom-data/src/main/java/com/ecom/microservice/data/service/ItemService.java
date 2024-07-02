package com.ecom.microservice.data.service;

import java.util.List;

import com.ecom.microservice.data.model.Item;

public interface ItemService {
    List<Item> searchAll();
}
