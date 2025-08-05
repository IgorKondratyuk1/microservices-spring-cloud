package com.orderService.client;

import java.util.List;

public record GetByIdsDto(
        List<Long> productIds
) {}
