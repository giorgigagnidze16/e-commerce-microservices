package com.ecom.microservice.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.ecom.microservice.api.exception.ResourceAlreadyExistsException;
import com.ecom.microservice.api.model.CategoryResponse;
import com.ecom.microservice.entity.Category;
import com.ecom.microservice.repository.CategoriesRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Categories service.
 */
@Service
@RequiredArgsConstructor
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;

    /**
     * Create a new category
     *
     * @return {@link CategoryResponse} optional
     */
    public Optional<CategoryResponse> create(@NotNull final String name) {
        if (categoriesRepository.findByName(name).isPresent()) {
            throw new ResourceAlreadyExistsException("Category '" + name + "' already exists");
        }
        Category category = categoriesRepository.save(new Category(name));
        return Optional.of(new CategoryResponse(category.getId(), category.getName()));
    }

    /**
     * Finds categories by given ids.
     *
     * @param ids of categories
     * @return list of found categories
     */
    public List<Category> findByIds(List<Long> ids) {
        return categoriesRepository.findAllById(ids);
    }

}
