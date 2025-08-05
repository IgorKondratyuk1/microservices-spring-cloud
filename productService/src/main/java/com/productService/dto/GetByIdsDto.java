package com.productService.dto;

import java.util.List;

public record GetByIdsDto(
        List<Long> productIds
) {}
