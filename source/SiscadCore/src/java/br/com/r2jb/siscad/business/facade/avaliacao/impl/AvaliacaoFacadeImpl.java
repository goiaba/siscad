/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.business.facade.avaliacao.impl;


import br.com.r2jb.siscad.business.entity.Avaliacao;
import br.com.r2jb.siscad.business.entity.Disciplina;
import br.com.r2jb.siscad.business.exception.EntidadePossuiRelacionamentoException;
import br.com.r2jb.siscad.business.facade.avaliacao.AvaliacaoFacade;
import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.dao.avaliacao.AvaliacaoDAO;
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
@Service("AvaliacaoFacade")
public class AvaliacaoFacadeImpl extends BaseFacadeImpl<Avaliacao, AvaliacaoDAO> implements AvaliacaoFacade {

    @Autowired private AvaliacaoDAO avaliacaoDao;
    
    @Override
    public List<Avaliacao> findAvaliaçõesPorDisciplina(Disciplina disciplina) {

        verifyArgument(disciplina, Disciplina.class);

        try {

            return avaliacaoDao.findAvaliacoesPorDisciplina(disciplina);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void remover(Avaliacao entity) throws EntidadePossuiRelacionamentoException {

        verifyArgument("entity", entity, Avaliacao.class);

        if (null == entity.getListaNota() || entity.getListaNota().isEmpty()) {

            entity = avaliacaoDao.attach(entity);
            avaliacaoDao.remove(entity);

        } else {

            EntidadePossuiRelacionamentoException e = new EntidadePossuiRelacionamentoException();
            e.addRelacionamentoPreenchido(EntidadePossuiRelacionamentoException.Relacionamento.AVALIACAO_NOTA);

            throw e;

        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void alterar(Avaliacao entity) {

        verifyArgument("entity", entity, Avaliacao.class);

        avaliacaoDao.merge(entity);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inserir(Avaliacao entity) {

        verifyArgument("entity", entity, Avaliacao.class);

        avaliacaoDao.save(entity);

    }

    @Override
    public boolean existsEntityWithSameUniqueKeyAttributes(Avaliacao entity) {

        verifyArgument("entity", entity, Avaliacao.class);

        try {

            return (null != avaliacaoDao.existsEntityWithSameUniqueKeyAttributes(entity)) ? true : false;

        } catch (EmptyResultDataAccessException ex) {

            return false;

        }

    }

    @Override
    public List<Avaliacao> findByExample(Avaliacao entity) {

        verifyArgument("entity", entity, Avaliacao.class);

        try {

            return avaliacaoDao.findByExample(entity);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    public void setAvaliacaoDAO(AvaliacaoDAO avaliacaoDAO) {
        this.avaliacaoDao = avaliacaoDAO;
    }
    
}
