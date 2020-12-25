package com.warehouseapi.models.converters;

import com.warehouseapi.base.WarehouseConverter;
import com.warehouseapi.models.dto.ProductDto;
import com.warehouseapi.models.entities.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class ProductDTOToProductEntity
        implements WarehouseConverter<ProductDto, ProductEntity> {
    @Override
    public ProductEntity convert(ProductDto input) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCode(input.getCode());
        productEntity.setName(input.getName());
        productEntity.setPrice(input.getPrice());
        productEntity.setStatus(input.getStatus());
        productEntity.setId(input.getId());
        productEntity.setUpdatedDate(input.getUpdatedAt());
        productEntity.setCreatedDate(Objects.isNull(input.getCreatedAt()) ? new Date() : input.getCreatedAt());
        productEntity.setVatAmount(input.getVatAmount());
        productEntity.setVatIncludedPrice(input.getVatIncludedPrice());
        productEntity.setVatRate(input.getVatRate());
        return productEntity;
    }
}
