package com.orderService.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record CreateOrderDto(
        Long id,
        LocalDateTime createdAt,
        String status,
        BigDecimal totalAmount,
        List<Long> productIds
) {}
