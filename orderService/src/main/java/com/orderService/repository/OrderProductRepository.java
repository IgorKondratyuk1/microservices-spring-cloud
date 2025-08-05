package com.orderService.repository;

import com.orderService.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Long> {

    Optional<OrderProductEntity> findByOrderIdAndProductId(Long orderId, Long productId);

    List<OrderProductEntity> findAllByOrderId(Long orderId);
}
