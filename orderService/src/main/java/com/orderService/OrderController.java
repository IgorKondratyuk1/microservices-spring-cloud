package com.orderService;

import com.orderService.dto.*;
import com.orderService.service.OrderProductService;
import com.orderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO API Gateway
// TODO Resilience4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderProductService orderProductService;

    @GetMapping
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody CreateOrderDto dto) {
        OrderDto created = orderService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public OrderDto update(@PathVariable Long id, @RequestBody CreateOrderDto dto) {
        return orderService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/products")
    public OrderWithProductsDto getOrderWithProductsById(@PathVariable Long id) {
        return orderService.getOrderWithProductsById(id);
    }

    @PostMapping("/{id}/products")
    public void addProductToOrder(@PathVariable Long id, @RequestBody AddProductToOrderDto addProductToOrderDto) {
        orderProductService.addProductToOrder(id, addProductToOrderDto.productId());
    }

    @DeleteMapping("/{id}/products")
    public void removeProductFromOrder(@PathVariable Long id, @RequestBody DeleteProductFromOrderDto deleteProductFromOrderDto) {
        orderProductService.removeProductFromOrder(id, deleteProductFromOrderDto.productId());
    }
}
