package com.orderService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderDto> getAll() {
        return orderRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public OrderDto getById(Long id) {
        return toDto(findById(id));
    }

    public OrderDto create(OrderDto dto) {
        OrderEntity entity = new OrderEntity();
        entity.setStatus(dto.status());
        entity.setTotalAmount(dto.totalAmount());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setProductIds(dto.productIds());
        return toDto(orderRepository.save(entity));
    }

    public OrderDto update(Long id, OrderDto dto) {
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
                entity.getTotalAmount(),
                entity.getProductIds()
        );
    }
}

