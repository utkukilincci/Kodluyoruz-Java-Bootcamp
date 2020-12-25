package com.warehouseapi.dao;

import com.warehouseapi.models.entities.WarehouseEntity;

import java.util.Collection;


public interface WarehouseRepository {

    Collection<WarehouseEntity> list();

    WarehouseEntity create(WarehouseEntity warehouseEntity);

    WarehouseEntity update(WarehouseEntity warehouseEntity);

    void delete(Long id);

}
