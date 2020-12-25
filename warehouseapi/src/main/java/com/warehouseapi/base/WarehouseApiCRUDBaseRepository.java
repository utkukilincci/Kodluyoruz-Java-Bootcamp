package com.warehouseapi.base;

import com.warehouseapi.models.entities.BaseEntity;

import java.util.Collection;

public interface WarehouseApiCRUDBaseRepository<T extends BaseEntity, ID extends  Long> {

    Collection<T> list();

    T create(T entity);

    T update(T entity);

    void delete(ID id);

}
