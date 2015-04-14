/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.alocacao.impl;

import br.com.r2jb.siscad.business.entity.Alocacao;
import br.com.r2jb.siscad.business.facade.alocacao.AlocacaoFacade;
import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.dao.alocacao.AlocacaoDAO;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Service("AlocacaoFacade")
public class AlocacaoFacadeImpl extends BaseFacadeImpl<Alocacao, AlocacaoDAO> implements AlocacaoFacade {

    @Autowired private AlocacaoDAO alocacaoDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void remover(Alocacao entity) {

        verifyArgument("entity", entity, Alocacao.class);

        entity = alocacaoDao.attach(entity);
        alocacaoDao.remove(entity);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void alterar(Alocacao entity) {

        verifyArgument("entity", entity, Alocacao.class);

        alocacaoDao.merge(entity);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inserir(Alocacao entity) {

        verifyArgument("entity", entity, Alocacao.class);

        alocacaoDao.save(entity);

    }

    @Override
    public boolean existsEntityWithSameUniqueKeyAttributes(Alocacao entity) {

        verifyArgument("entity", entity, Alocacao.class);

        try {

            return (null != alocacaoDao.existsEntityWithSameUniqueKeyAttributes(entity)) ? true : false;

        } catch (EmptyResultDataAccessException ex) {

            return false;

        }

    }

    @Override
    public List<Alocacao> findByExample(Alocacao entity) {

        verifyArgument("entity", entity, Alocacao.class);

        try {

            return alocacaoDao.findByExample(entity);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

}
