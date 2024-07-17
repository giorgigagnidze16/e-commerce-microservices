package com.ecom.microservice.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//TODO add reviews relation after introducing users / sellers concept
@Entity
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 3, max = 400)
    private String title;

    @Column(nullable = false)
    @Size(min = 50, max = 760)
    private String description;

    @Column(nullable = false)
    @DecimalMin(value = "0.01")
    private BigDecimal price;

    @Column
    @DecimalMin(value = "0.00")
    private BigDecimal discount;

    @Column(nullable = false)
    @Min(0)
    private Long stock;

    @Column(columnDefinition = "boolean default false")
    private Boolean archived;

    @Column(updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @ToString.Exclude
    private Set<Image> images;

    @ManyToOne
    @ToString.Exclude
    private Manufacturer manufacturer;

    public Product(String title, String description,
                   BigDecimal price, BigDecimal discount,
                   Long stock, Boolean archived,
                   Manufacturer manufacturer) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.stock = stock;
        this.archived = archived;
        this.manufacturer = manufacturer;
    }

    public Product() {
    }

}
