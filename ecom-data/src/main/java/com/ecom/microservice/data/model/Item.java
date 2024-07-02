package com.ecom.microservice.data.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

//TODO add reviews relation
@Entity
@Getter
@Setter
@ToString
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Size(min = 3, max = 250)
    private String title;

    @Column
    @Size(min = 50, max = 500)
    private String description;

    @Column
    @DecimalMin(value = "0.01")
    private BigDecimal price;

    @Column
    @DecimalMin(value = "0.00")
    private BigDecimal discount;

    @Column
    @Min(0)
    private Long stock;

    @Column(columnDefinition = "boolean default false")
    private Boolean archived;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
