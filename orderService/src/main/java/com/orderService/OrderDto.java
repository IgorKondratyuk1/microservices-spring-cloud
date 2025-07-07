package com.orderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(
        Long id,
        LocalDateTime createdAt,
        String status,
        BigDecimal totalAmount,
        List<Long> productIds
) {}
