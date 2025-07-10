package com.orderService.dto;

import com.orderService.client.ProductDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderWithProductsDto(
        Long id,
        LocalDateTime createdAt,
        String status,
        BigDecimal totalAmount,
        List<ProductDto> products
) {}
