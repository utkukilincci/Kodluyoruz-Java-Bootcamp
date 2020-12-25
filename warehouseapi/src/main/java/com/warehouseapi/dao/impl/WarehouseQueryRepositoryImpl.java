package com.warehouseapi.dao.impl;

import com.warehouseapi.base.AbstractWarehouseApiCRUD;
import com.warehouseapi.dao.WarehouseQueryRepository;
import com.warehouseapi.models.entities.WarehouseEntity;

public class WarehouseQueryRepositoryImpl
        extends AbstractWarehouseApiCRUD<WarehouseEntity, Long>
        implements WarehouseQueryRepository {
    @Override
    public boolean hasExistSameWarehouseCode(String warehouseCode) {
        Long result = getSession().createQuery("select count(w) from WarehouseEntity w" +
                " where w.code=:code", Long.class).setParameter("code",warehouseCode).getSingleResult();
        return result > 0;
    }

    @Override
    public boolean hasExistProduct(Long id) {
        Long result = getSession().createQuery("select count(p) from ProductEntity p " +
                "where p.id=:productId", Long.class)
                .setParameter("productId",id)
                .getSingleResult();
        return result <= 0;
    }

    @Override
    public boolean hasExistWarehouse(Long id) {
        Long result = getSession().createQuery("select count(w) from WarehouseEntity w " +
                "where w.id=:warehouseId", Long.class)
                .setParameter("warehouseId",id)
                .getSingleResult();
        return result <= 0;
    }
}
