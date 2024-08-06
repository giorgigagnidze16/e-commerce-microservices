package com.ecom.microservice.repository;

import java.util.Optional;

import com.ecom.microservice.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Role repository.
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    /**
     * Find role by name.
     *
     * @param name of role
     * @return found entity
     */
    Optional<RoleEntity> findByName(String name);
}
