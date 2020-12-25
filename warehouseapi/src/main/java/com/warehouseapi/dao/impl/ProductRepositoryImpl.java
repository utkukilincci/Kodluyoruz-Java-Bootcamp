package com.warehouseapi.dao.impl;

import com.warehouseapi.base.AbstractWarehouseApiCRUD;
import com.warehouseapi.dao.ProductRepository;
import com.warehouseapi.models.entities.ProductEntity;
import org.springframework.stereotype.Repository;


@Repository
public class ProductRepositoryImpl
        extends AbstractWarehouseApiCRUD<ProductEntity, Long>
        implements ProductRepository {

}
