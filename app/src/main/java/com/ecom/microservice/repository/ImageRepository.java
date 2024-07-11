package com.ecom.microservice.repository;

import com.ecom.microservice.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Image JPA Repository.
 */
public interface ImageRepository extends JpaRepository<Image, Long> {
}
