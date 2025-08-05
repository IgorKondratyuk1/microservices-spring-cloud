package com.orderService.service;

import com.orderService.client.GetByIdsDto;
import com.orderService.client.ProductClient;
import com.orderService.client.ProductDto;
import com.orderService.entity.OrderProductEntity;
import com.orderService.repository.OrderProductRepository;
import com.orderService.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderProductService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductClient productClient;

    public void addProductToOrder(Long orderId, Long productId) {
        this.orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("No order with id " + orderId));

        OrderProductEntity orderProductEntity = new OrderProductEntity(orderId, productId);
        orderProductRepository.save(orderProductEntity);
    }

    public void removeProductFromOrder(Long orderId, Long productId) {
        OrderProductEntity foundedOrderProductEntity = orderProductRepository.findByOrderIdAndProductId(orderId, productId)
                .orElseThrow(() -> new RuntimeException("Not found product " + productId + " in order " + orderId));

        orderProductRepository.deleteById(foundedOrderProductEntity.getId());
    }

    public List<ProductDto> findProductsForOrder(Long orderId) {
        this.orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("No order with id " + orderId));

        List<OrderProductEntity> orderProductEntities = this.orderProductRepository.findAllByOrderId(orderId);
        List<Long> productIds = orderProductEntities.stream().map(order -> order.getProductId()).toList();

        GetByIdsDto requestDto = new GetByIdsDto(productIds);
        return this.productClient.getAllByIds(requestDto);
    }
}
