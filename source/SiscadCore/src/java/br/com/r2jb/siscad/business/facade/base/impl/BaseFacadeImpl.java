/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.base.impl;


import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.business.entity.BaseEntity;
import br.com.r2jb.siscad.business.facade.base.BaseFacade;
import br.com.r2jb.siscad.business.exception.ArgumentoNuloException;
import br.com.r2jb.siscad.dao.base.BaseDAO;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Service("BaseFacade")
public abstract class BaseFacadeImpl<T extends BaseEntity, U extends BaseDAO<T>> implements BaseFacade<T>, Serializable {

    private Class<U> clazz;

    public BaseFacadeImpl() {

        this.clazz = (Class<U>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];

    }

    @Override
    @Transactional(readOnly=true)
    public T find(Integer id) {

        verifyArgument(id, Integer.class);

        return (T) ServiceLocator.getDAO(clazz).find(id);

    }

    @Override
    @Transactional(readOnly=true)
    public List<T> findAll() {

        return findAll(0,0);

    }

    @Override
    @Transactional(readOnly=true)
    public List<T> findAll(int start, int max) {

        try {

            return ServiceLocator.getDAO(clazz).findAll(start, max);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    public void verifyArgument(String argumentName, Object argumentValue, Class argumentType) {

        if (null == argumentValue) {

            throw new ArgumentoNuloException(argumentName, argumentType);

        }

    }

    public void verifyArgument(Object argumentValue, Class argumentType) {

        if (null == argumentValue) {

            throw new ArgumentoNuloException(argumentType);

        }

    }

    public void verifyArgument(Object argumentValue) {

        if (null == argumentValue) {

            throw new ArgumentoNuloException();

        }

    }

}
