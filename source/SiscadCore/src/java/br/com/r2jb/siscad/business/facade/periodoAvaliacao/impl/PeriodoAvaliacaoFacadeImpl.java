/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.periodoAvaliacao.impl;


import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.PeriodoAvaliacao;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.exception.EntidadePossuiRelacionamentoException;
import br.com.r2jb.siscad.business.exception.UniqueKeyConstraintViolatedException;
import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.business.facade.periodoAvaliacao.PeriodoAvaliacaoFacade;
import br.com.r2jb.siscad.business.util.Ano;
import br.com.r2jb.siscad.business.util.Periodo;
import br.com.r2jb.siscad.dao.periodoAvaliacao.PeriodoAvaliacaoDAO;
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
@Service("PeriodoAvaliacaoFacade")
public class PeriodoAvaliacaoFacadeImpl extends BaseFacadeImpl<PeriodoAvaliacao, PeriodoAvaliacaoDAO> implements PeriodoAvaliacaoFacade {

    @Autowired private PeriodoAvaliacaoDAO periodoAvaliacaoDao;
    
    @Override
    public List<Ano> findAnosPorProfessor(Professor professor) {

        verifyArgument(professor, Professor.class);
        
        try {

            return periodoAvaliacaoDao.findAnosPorProfessor(professor);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public List<Periodo> findSemestresPorProfessor(Professor professor) {

        verifyArgument(professor, Professor.class);
        
        try {

            return periodoAvaliacaoDao.findSemestresPorProfessor(professor);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public List<Periodo> findSemestres(Professor professor, Escola escola, Curso curso, Ano ano) {

        try {

            return periodoAvaliacaoDao.findSemestres(professor, escola, curso, ano);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public List<Ano> findAnos(Professor professor, Escola escola, Curso curso, Periodo semestre) {

        try {

            return periodoAvaliacaoDao.findAnos(professor, escola, curso, semestre);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public List<PeriodoAvaliacao> findByCursoPeriodoEAno(Curso curso, Periodo periodo, Ano ano) {

        verifyArgument("curso", curso, Curso.class);
        verifyArgument("periodo", periodo, Periodo.class);
        verifyArgument("ano", ano, Ano.class);

        try {

            return periodoAvaliacaoDao.findByCursoPeriodoEAno(curso, periodo, ano);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public List<PeriodoAvaliacao> findByCurso(Curso curso, Boolean anoAtualOuMaisRecente) {

        verifyArgument("curso", curso, Curso.class);

        try {

            return periodoAvaliacaoDao.findByCurso(curso, anoAtualOuMaisRecente);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public boolean existsEntityWithSameUniqueKeyAttributes(PeriodoAvaliacao entity) {

        verifyArgument("entity", entity, PeriodoAvaliacao.class);

        try {

            return (null != periodoAvaliacaoDao.existsEntityWithSameUniqueKeyAttributes(entity)) ? true : false;

        } catch (EmptyResultDataAccessException ex) {

            return false;

        }

    }

    @Override
    public List<PeriodoAvaliacao> findByExample(PeriodoAvaliacao entity) {

        verifyArgument("entity", entity, PeriodoAvaliacao.class);

        try {

            return periodoAvaliacaoDao.findByExample(entity);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inserir(PeriodoAvaliacao entity) throws UniqueKeyConstraintViolatedException {

        verifyArgument("entity", entity, PeriodoAvaliacao.class);

        if (existsEntityWithSameUniqueKeyAttributes(entity)) {

            throw new UniqueKeyConstraintViolatedException();

        }

        periodoAvaliacaoDao.save(entity);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void alterar(PeriodoAvaliacao entity) throws UniqueKeyConstraintViolatedException {

        verifyArgument("entity", entity, PeriodoAvaliacao.class);

        if (existsEntityWithSameUniqueKeyAttributes(entity)) {

            throw new UniqueKeyConstraintViolatedException();

        }

        periodoAvaliacaoDao.merge(entity);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void remover(PeriodoAvaliacao entity) throws EntidadePossuiRelacionamentoException {

        verifyArgument("entity", entity, PeriodoAvaliacao.class);

        if (null == entity.getListaAvaliacao() || entity.getListaAvaliacao().isEmpty()) {

            entity = periodoAvaliacaoDao.attach(entity);
            periodoAvaliacaoDao.remove(entity);

        } else {

            EntidadePossuiRelacionamentoException e = new EntidadePossuiRelacionamentoException();
            e.addRelacionamentoPreenchido(EntidadePossuiRelacionamentoException.Relacionamento.PERIODOAVALIACAO_AVALIACAO);

            throw e;

        }

    }

    public void setPeriodoAvaliacaoDAO(PeriodoAvaliacaoDAO periodoAvaliacaoDAO) {
        this.periodoAvaliacaoDao = periodoAvaliacaoDAO;
    }

}
