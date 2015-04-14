/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.base;

import br.com.r2jb.siscad.business.entity.BaseEntity;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface BaseDAO<T extends BaseEntity> {

    T existsEntityWithSameUniqueKeyAttributes(T entity);
    
    T find(Integer id);
    List<T> findAll();
    List<T> findAll(int start, int max);
    void save(T object);
    void remove(T object);
    T merge(T object);
    T attach(T object);
    void flush();

}
