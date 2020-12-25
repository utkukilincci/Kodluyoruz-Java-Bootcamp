package com.warehouseapi.models.converters;

import com.warehouseapi.base.WarehouseConverter;
import com.warehouseapi.models.dto.WarehouseDto;
import com.warehouseapi.models.entities.WarehouseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class WarehouseDTOToWarehouseEntity implements
        WarehouseConverter<WarehouseDto, WarehouseEntity> {

    @Override
    public WarehouseEntity convert(WarehouseDto input) {
        WarehouseEntity warehouseEntity = new WarehouseEntity();
        warehouseEntity.setId(input.getId());
        warehouseEntity.setName(input.getName());
        warehouseEntity.setCode(input.getCode());
        warehouseEntity.setStatus(input.getStatus());
        warehouseEntity.setCreatedDate(Objects.isNull(input.getCreatedAt()) ? new Date() : input.getCreatedAt());

        warehouseEntity.setUpdatedDate(input.getUpdatedAt());
        return warehouseEntity;
    }
}
