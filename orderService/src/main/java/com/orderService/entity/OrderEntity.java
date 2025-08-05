package com.orderService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt = LocalDateTime.now();

    private String status;

    private BigDecimal totalAmount;

//    @NotEmpty
//    @ElementCollection
//    @CollectionTable(name = "order_products", joinColumns = @JoinColumn(name = "order_id"))
//    @Column(name = "product_id")
//    List<Long> productIds = new ArrayList<>();
}