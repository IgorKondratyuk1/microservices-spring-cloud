package com.productService;

import com.productService.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public ProductDto getById(Long id) {
        return toDto(findById(id));
    }

    public List<ProductDto> getByIds(List<Long> ids) {
        return this.productRepository.findAllById(ids).stream()
                .map(productEntity -> toDto(productEntity))
                .toList();
    }

    public ProductDto create(ProductDto dto) {
        ProductEntity entity = toEntity(dto);
        return toDto(productRepository.save(entity));
    }

    public ProductDto update(Long id, ProductDto dto) {
        ProductEntity existing = findById(id);
        existing.setName(dto.name());
        existing.setDescription(dto.description());
        existing.setPrice(dto.price());
        existing.setAvailable(dto.available());
        return toDto(productRepository.save(existing));
    }

    public void delete(Long id) {
        productRepository.delete(findById(id));
    }

    private ProductEntity findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    private ProductDto toDto(ProductEntity entity) {
        return new ProductDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.isAvailable()
        );
    }

    private ProductEntity toEntity(ProductDto dto) {
        ProductEntity entity = new ProductEntity();
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setPrice(dto.price());
        entity.setAvailable(dto.available());
        return entity;
    }
}

