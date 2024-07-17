package com.ecom.microservice.repository;

import java.util.Optional;

import com.ecom.microservice.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Manufacturer repository.
 */
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    /**
     * Finds a manufacturer by name.
     *
     * @param name of manufacturer
     * @return manufacturer optional
     */
    Optional<Manufacturer> findByName(String name);
}
