package com.ecom.microservice.repository;

import java.math.BigDecimal;
import java.util.List;

import com.ecom.microservice.entity.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Product repository
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Full text search of products with product's title and its description,
     * multiple filters applied.
     *
     * @param query          search query string
     * @param minPrice       minimum price of product
     * @param maxPrice       maximum price of product
     * @param manufacturerId of product
     * @param categoryIds    of product
     * @return List of found products
     */
    @Query(value = """
        SELECT p.id, p.title, p.description, p.price, p.discount, p.stock, p.archived, p.created_at, p.updated_at, p.manufacturer_id
        FROM ecommo.product p
                 INNER JOIN (SELECT DISTINCT p.id
                             FROM ecommo.product p
                                      LEFT JOIN ecommo.product_categories_rel pcr
                                                 ON p.id = pcr.product_id
                             WHERE :categoryIds IS NULL OR pcr.category_id IN (:categoryIds)) sub
                            ON p.id = sub.id
        WHERE to_tsvector(p.title || ' ' || p.description) @@ websearch_to_tsquery(:query)
          AND p.archived IS FALSE
          AND (:minPrice IS NULL OR p.price >= :minPrice)
          AND (:maxPrice IS NULL OR p.price <= :maxPrice)
          AND (:manufacturerId IS NULL OR p.manufacturer_id = :manufacturerId)
        ORDER BY ts_rank(to_tsvector(p.title || ' ' || p.description), websearch_to_tsquery(:query)) DESC;
                """,
        nativeQuery = true)
    List<Product> findByQuery(
        @Param("query") String query,
        @Param("minPrice") BigDecimal minPrice,
        @Param("maxPrice") BigDecimal maxPrice,
        @Param("manufacturerId") Long manufacturerId,
        @Param("categoryIds") List<Long> categoryIds,
        Pageable pageable
    );


    /**
     * Finds all active products in the market.
     *
     * @return List of found products
     */
    List<Product> findAllByArchived(boolean archived, PageRequest pageRequest);

    /**
     * @param id       of product
     * @param archived new product status true/false
     * @return number of updated rows
     * @see Product
     */
    @Modifying
    @Query("UPDATE Product p set p.archived= :archived where p.id = :id")
    int updateVisibility(Long id, boolean archived);


    /**
     * @param id    of product
     * @param price new product price
     * @return number of updated rows
     * @see Product
     */
    @Modifying
    @Query("UPDATE Product p set p.price= :price where p.id= :id")
    int updatePrice(Long id, Double price);
}
