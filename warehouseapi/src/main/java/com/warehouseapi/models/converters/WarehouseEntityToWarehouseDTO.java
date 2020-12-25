package com.warehouseapi.models.converters;

import com.warehouseapi.base.WarehouseConverter;
import com.warehouseapi.models.dto.WarehouseDto;
import com.warehouseapi.models.entities.WarehouseEntity;
import org.springframework.stereotype.Component;

@Component
public class WarehouseEntityToWarehouseDTO implements
        WarehouseConverter<WarehouseEntity, WarehouseDto> {
    @Override
    public WarehouseDto convert(WarehouseEntity input) {
        WarehouseDto warehouseDto = new WarehouseDto();
        warehouseDto.setId(input.getId());
        warehouseDto.setName(input.getName());
        warehouseDto.setCode(input.getCode());
        warehouseDto.setStatus(input.getStatus());
        warehouseDto.setCreatedAt(input.getCreatedDate());
        warehouseDto.setUpdatedAt(input.getUpdatedDate());
        return warehouseDto;
    }
}
