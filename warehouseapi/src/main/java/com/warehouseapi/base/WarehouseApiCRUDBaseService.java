package com.warehouseapi.base;


import com.warehouseapi.models.dto.BaseIdDto;

import java.util.Collection;

public interface WarehouseApiCRUDBaseService<T extends BaseIdDto> {

    WarehouseApiResponse<Collection<T>> list();

    WarehouseApiResponse<T> create(T data);

    WarehouseApiResponse<T> update(T data);

    WarehouseApiResponse<?> delete(BaseIdDto data);
}
