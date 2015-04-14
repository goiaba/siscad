/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.base.impl;

import br.com.r2jb.siscad.business.entity.BaseEntity;
import br.com.r2jb.siscad.dao.base.BaseDAO;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Repository("BaseDAO")
public abstract class BaseDAOImpl<T extends BaseEntity> implements BaseDAO<T>, Serializable {

    private Class<T> clazz;
    private EntityManager entityManager;

    public BaseDAOImpl() {

        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    @Override
    public T find(Integer id) {

        return (T) getEntityManager().find(clazz, id);

    }

    @Override
    public T existsEntityWithSameUniqueKeyAttributes(T entity) {
        throw new RuntimeException("Sobrescrever método.");
    }
    
    @Override
    public List<T> findAll() {
        
        String entityName = clazz.getSimpleName();
        
        String query = "SELECT entity" +
                       " FROM " + entityName + " entity";
        
        return getEntityManager()
                .createQuery(query)
                .getResultList();
        
    }

    @Override
    public List<T> findAll(int start, int max) {

        String entityName = clazz.getSimpleName();

        String query = "SELECT entity" +
                       " FROM " + entityName + " entity";

        return getEntityManager()
                .createQuery(query)
                .setFirstResult(start)
                .setMaxResults(max)
                .getResultList();

    }

    @PersistenceContext
    public void setEntityManager(EntityManager em) {

        this.entityManager = em;

    }

    protected EntityManager getEntityManager() {

        return entityManager;
        
    }

    @Override
    public void save(T object) {

        getEntityManager().persist(object);

    }

    @Override
    public T merge(T object) {

        return getEntityManager().merge(object);

    }

    @Override
    public T attach(T object) {

        return getEntityManager().merge(object);

    }

    @Override
    public void remove(T object) {

        getEntityManager().remove(object);

    }

    @Override
    public void flush() {

        getEntityManager().flush();

    }

}
