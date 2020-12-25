package com.warehouseapi.models.dto;

import com.warehouseapi.models.enums.WarehouseStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseDto extends BaseIdDto {
    private String name;
    private String code;
    private WarehouseStatusEnum status;
}
