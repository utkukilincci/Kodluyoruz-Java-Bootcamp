package com.warehouseapi.base;

public interface WarehouseConverter<T, R> {

    R convert(T input);
}
