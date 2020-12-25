package com.warehouseapi.base;

import com.warehouseapi.models.entities.BaseEntity;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

@Transactional(readOnly = true)
public abstract class AbstractWarehouseApiCRUD<T extends BaseEntity, ID extends Long>
        implements WarehouseApiCRUDBaseRepository<T,ID> {

    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> entity;

    @SuppressWarnings("unchecked")
    public AbstractWarehouseApiCRUD() {
        this.entity = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Collection<T> list() {
        return getSession().createQuery("from " +
                entity.getName(), entity).getResultList();
    }

    @Override
    @Transactional
    public T create(T entity) {
        getSession().persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public T update(T entity) {
        getSession().update(entity);
        return entity;
    }

    @Override
    @Transactional
    public void delete(ID id) {
        getSession()
                .createQuery("update " + entity.getName() + " set status = 'DELETED' where id=:entityId ")
                .setParameter("entityId", id)
                .executeUpdate();
    }

}
