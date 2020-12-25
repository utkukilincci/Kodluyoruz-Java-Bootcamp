package com.warehouseapi.models.converters;

import com.warehouseapi.base.WarehouseConverter;
import com.warehouseapi.models.dto.ProductDto;
import com.warehouseapi.models.entities.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class ProductEntityToProductDto
        implements WarehouseConverter<ProductEntity, ProductDto> {
    @Override
    public ProductDto convert(ProductEntity input) {
        ProductDto productDto = new ProductDto();
        productDto.setCode(input.getCode());
        productDto.setName(input.getName());
        productDto.setPrice(input.getPrice());
        productDto.setStatus(input.getStatus());
        productDto.setId(input.getId());
        productDto.setUpdatedAt(input.getUpdatedDate());
        productDto.setCreatedAt(Objects.isNull(input.getCreatedDate()) ? new Date() : input.getCreatedDate());
        productDto.setVatAmount(input.getVatAmount());
        productDto.setVatIncludedPrice(input.getVatIncludedPrice());
        productDto.setVatRate(input.getVatRate());
        return productDto;
    }
}
