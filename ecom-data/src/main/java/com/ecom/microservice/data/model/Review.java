package com.ecom.microservice.data.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

//@Entity
//@Getter
//@Setter
//@ToString
//public class Review {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column
//    @Min(0)
//    @Max(5)
//    private Integer rating;
//
//    @ManyToOne
//    @JoinColumn(name="item_id", nullable=false)
//    private Item item;
//
//    @Column(updatable = false)
//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @Column
//    @LastModifiedDate
//    private LocalDateTime updatedAt;
//}
