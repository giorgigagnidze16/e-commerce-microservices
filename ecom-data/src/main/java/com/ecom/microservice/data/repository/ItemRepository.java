package com.ecom.microservice.data.repository;

import com.ecom.microservice.data.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
