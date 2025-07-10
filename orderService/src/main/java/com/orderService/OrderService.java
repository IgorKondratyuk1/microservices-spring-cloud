package com.orderService;

import com.orderService.client.ProductClient;
import com.orderService.dto.CreateOrderDto;
import com.orderService.dto.OrderDto;
import com.orderService.dto.OrderWithProductsDto;
import com.orderService.client.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public List<OrderDto> getAll() {
        return orderRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public OrderDto getById(Long id) {
        return toDto(findById(id));
    }

    public OrderWithProductsDto getOrderWithProductsById(Long id) {
        OrderEntity orderEntity = findById(id);
        List<ProductDto> productDtos = new ArrayList<>();

        orderEntity.getProductIds().forEach(productId -> {
            ProductDto productDto = productClient.getById(productId);
            System.out.println(productDto);
            productDtos.add(productDto);
        });
        
        return toWithProductsDto(orderEntity, productDtos);
    }

    public OrderDto create(CreateOrderDto dto) {
        OrderEntity entity = new OrderEntity();
        entity.setStatus(dto.status());
        entity.setTotalAmount(dto.totalAmount());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setProductIds(dto.productIds());
        return toDto(orderRepository.save(entity));
    }

    public OrderDto update(Long id, CreateOrderDto dto) {
        OrderEntity entity = findById(id);
        entity.setStatus(dto.status());
        entity.setTotalAmount(dto.totalAmount());
        entity.setProductIds(dto.productIds());
        return toDto(orderRepository.save(entity));
    }

    public void delete(Long id) {
        orderRepository.delete(findById(id));
    }

    private OrderEntity findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    private OrderDto toDto(OrderEntity entity) {
        return new OrderDto(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getStatus(),
                entity.getTotalAmount()
        );
    }

    private OrderWithProductsDto toWithProductsDto(OrderEntity entity, List<ProductDto> productDtos) {
        return new OrderWithProductsDto(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getStatus(),
                entity.getTotalAmount(),
                productDtos
        );
    }
}

