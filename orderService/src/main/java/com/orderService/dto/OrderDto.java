package com.orderService.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderDto(
        Long id,
        LocalDateTime createdAt,
        String status,
        BigDecimal totalAmount
) {}
