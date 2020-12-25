package com.warehouseapi.dao;

public interface WarehouseQueryRepository {
    boolean hasExistSameWarehouseCode(String warehouseCode);
    boolean hasExistProduct(Long id);
    boolean hasExistWarehouse(Long id);
}
