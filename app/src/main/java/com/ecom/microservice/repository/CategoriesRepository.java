package com.ecom.microservice.repository;

import java.util.Optional;

import com.ecom.microservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Categories repository.
 */
public interface CategoriesRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
