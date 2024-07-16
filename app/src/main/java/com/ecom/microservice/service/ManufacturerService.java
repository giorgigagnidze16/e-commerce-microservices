package com.ecom.microservice.service;

import com.ecom.microservice.entity.Manufacturer;
import com.ecom.microservice.repository.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Manufacturer service.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ManufacturerService {
    private final ManufacturerRepository repository;

    /**
     * Finds a manufacturer in the database if present,
     * creates new one with an appropriate name
     * if requested manufacturer does not exist.
     *
     * @param name of manufacturer
     * @return found manufacturer
     */
    public Manufacturer findByName(final String name) {
        return repository.findByName(name).orElseGet(() -> {
            log.info("New manufacturer added: {}", name);
            return repository.save(new Manufacturer(name));
        });
    }
}
