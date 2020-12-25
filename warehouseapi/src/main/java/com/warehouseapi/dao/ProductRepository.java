package com.warehouseapi.dao;


import com.warehouseapi.models.entities.ProductEntity;

import java.util.Collection;

public interface ProductRepository {

    Collection<ProductEntity> list();

    ProductEntity create(ProductEntity productEntity);

    ProductEntity update(ProductEntity productEntity);

    void delete(Long id);
}
