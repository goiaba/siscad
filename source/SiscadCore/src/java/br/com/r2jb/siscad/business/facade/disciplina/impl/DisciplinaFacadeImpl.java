/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.disciplina.impl;


import br.com.r2jb.siscad.business.entity.Disciplina;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.entity.Turma;
import br.com.r2jb.siscad.business.exception.EntidadePossuiRelacionamentoException;
import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.business.facade.disciplina.DisciplinaFacade;
import br.com.r2jb.siscad.dao.disciplina.DisciplinaDAO;
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
@Service("DisciplinaFacade")
public class DisciplinaFacadeImpl extends BaseFacadeImpl<Disciplina, DisciplinaDAO> implements DisciplinaFacade {

    @Autowired private DisciplinaDAO disciplinaDao;

    @Override
    public List<Disciplina> findByProfessorETurma(Professor professor, Turma turma) {

        verifyArgument(turma, Turma.class);
        verifyArgument(professor, Professor.class);

        try {

            return disciplinaDao.findDisciplinasPorProfessorETurma(professor, turma);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public List<Disciplina> findByTurma(Turma turma) {

        verifyArgument(turma, Turma.class);

        try {

            return disciplinaDao.findDisciplinasPorTurma(turma);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public boolean existsEntityWithSameUniqueKeyAttributes(Disciplina entity) {

        verifyArgument("entity", entity, Disciplina.class);

        try {

            return (null != disciplinaDao.existsEntityWithSameUniqueKeyAttributes(entity)) ? true : false;

        } catch (EmptyResultDataAccessException ex) {

            return false;

        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inserir(Disciplina entity) {

        verifyArgument("entity", entity, Disciplina.class);

        disciplinaDao.save(entity);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void alterar(Disciplina entity) {

        verifyArgument("entity", entity, Disciplina.class);

        disciplinaDao.merge(entity);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void remover(Disciplina entity) throws EntidadePossuiRelacionamentoException {

        verifyArgument("entity", entity, Disciplina.class);

        if ((null == entity.getListaAlocacao() || entity.getListaAlocacao().isEmpty()) && (null == entity.getMatrizCurricularDisciplinaCollection() || entity.getMatrizCurricularDisciplinaCollection().isEmpty())) {

            entity = disciplinaDao.attach(entity);
            disciplinaDao.remove(entity);

        } else {

            EntidadePossuiRelacionamentoException e = new EntidadePossuiRelacionamentoException();
            e.addRelacionamentoPreenchido(EntidadePossuiRelacionamentoException.Relacionamento.AVALIACAO_NOTA);
            e.addRelacionamentoPreenchido(EntidadePossuiRelacionamentoException.Relacionamento.MATRIZCURRICULAR_DISCIPLINA);

            throw e;

        }

    }

    @Override
    public List<Disciplina> findByExample(Disciplina entity) {

        verifyArgument("entity", entity, Disciplina.class);

        try {

            return disciplinaDao.findByExample(entity);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    public void setDisciplinaDAO(DisciplinaDAO disciplinaDAO) {
        this.disciplinaDao = disciplinaDAO;
    }

}