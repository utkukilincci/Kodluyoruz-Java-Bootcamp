package com.warehouseapi.base;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WarehouseApiResponseError {

    private String code;
    private String message;
}
