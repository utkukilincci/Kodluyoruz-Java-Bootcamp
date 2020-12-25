package com.warehouseapi.dao.impl;

import com.warehouseapi.base.AbstractWarehouseApiCRUD;
import com.warehouseapi.dao.WarehouseRepository;
import com.warehouseapi.models.entities.WarehouseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class WarehouseRepositoryImpl
        extends AbstractWarehouseApiCRUD<WarehouseEntity, Long>
        implements WarehouseRepository {

}
