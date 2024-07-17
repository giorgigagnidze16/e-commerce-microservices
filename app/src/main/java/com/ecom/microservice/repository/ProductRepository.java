package com.ecom.microservice.repository;

import java.util.List;

import com.ecom.microservice.entity.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Product repository
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Finds all active products in the market.
     *
     * @return List of found products
     */
    List<Product> findAllByArchived(boolean archived, PageRequest pageRequest);

    /**
     *
     * @param id of product
     * @param archived new product status true/false
     * @return number of updated rows
     * @see Product
     */
    @Modifying
    @Query("UPDATE Product p set p.archived= :archived where p.id = :id")
    int updateVisibility(Long id, boolean archived);


    /**
     *
     * @param id of product
     * @param price new product price
     * @return number of updated rows
     * @see Product
     */
    @Modifying
    @Query("UPDATE Product p set p.price= :price where p.id= :id")
    int updatePrice(Long id, Double price);
}
