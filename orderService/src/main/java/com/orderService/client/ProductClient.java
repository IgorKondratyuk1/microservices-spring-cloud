package com.orderService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service1", url = "${product.service.url}")
public interface ProductClient {

    // TODO: many-to-many
    @GetMapping("/products/{id}")
    ProductDto getById(@PathVariable Long id);
}
