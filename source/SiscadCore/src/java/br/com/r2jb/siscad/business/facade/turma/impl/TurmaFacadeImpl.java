/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.business.facade.turma.impl;

import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.entity.Serie;
import br.com.r2jb.siscad.business.entity.Turma;
import br.com.r2jb.siscad.business.entity.Turno;
import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.business.facade.turma.TurmaFacade;
import br.com.r2jb.siscad.business.util.Ano;
import br.com.r2jb.siscad.business.util.Periodo;
import br.com.r2jb.siscad.business.util.StatusTurma;
import br.com.r2jb.siscad.dao.turma.TurmaDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Service("TurmaFacade")
public class TurmaFacadeImpl extends BaseFacadeImpl<Turma, TurmaDAO> implements TurmaFacade {

    @Autowired
    private TurmaDAO turmaDao;

    @Override
    public List<Turma> findByProfessorEscolaCursoETurno(Professor professor, Escola escola, Curso curso, Turno turno) {

        verifyArgument("professor", professor, Professor.class);
        verifyArgument("escola", escola, Escola.class);
        verifyArgument("curso", curso, Curso.class);
        verifyArgument("turno", turno, Turno.class);

        try {

            return turmaDao.findTurmasPorProfessorEscolaCursoETurno(professor, escola, curso, turno);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public List<Turma> findByProfessorEscolaECurso(Professor professor, Escola escola, Curso curso) {

        verifyArgument("professor", professor, Professor.class);
        verifyArgument("escola", escola, Escola.class);
        verifyArgument("curso", curso, Curso.class);

        try {

            return turmaDao.findTurmasPorProfessorEscolaECurso(professor, escola, curso);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public List<Turma> findByProfessorEscolaCursoSemestreEAno(Professor professor, Escola escola, Curso curso, Periodo periodo, Ano ano) {

        try {

            return turmaDao.findTurmas(professor, escola, curso, periodo, ano);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public List<Turma> findTurmasAtivasByEscola(Escola escola) {

        verifyArgument(escola, Escola.class);

        try {

            return turmaDao.findTurmasAtivasPorEscola(escola);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public List<Turma> findTurmasByEscola(Escola escola) {

        verifyArgument(escola, Escola.class);

        try {

            return turmaDao.findTurmasPorEscola(escola);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public List<Turma> findByEscolaCursoSerieEStatus(Escola escola, Curso curso, Serie serie, StatusTurma status) {

        verifyArgument("escola", escola, Escola.class);
        verifyArgument("curso", curso, Curso.class);
        verifyArgument("serie", serie, Serie.class);
        verifyArgument("status", status, StatusTurma.class);

        try {

            return turmaDao.findTurmasPorEscolaCursoSerieEStatus(escola, curso, serie, status);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public List<Turma> findByEscolaCursoSerie(Escola escola, Curso curso, Serie serie) {

        verifyArgument("escola", escola, Escola.class);
        verifyArgument("curso", curso, Curso.class);
        verifyArgument("serie", serie, Serie.class);

        try {

            return turmaDao.findTurmasPorEscolaCursoSerie(escola, curso, serie);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public List<Turma> findByEscolaCursoSerieAnoSemestre(Escola escola, Curso curso, Serie serie, Ano ano, Periodo periodo) {

        verifyArgument("escola", escola, Escola.class);
        verifyArgument("curso", curso, Curso.class);
        verifyArgument("serie", serie, Serie.class);
        verifyArgument("ano", ano, Ano.class);
        verifyArgument("periodo", periodo, Periodo.class);

        try {

            return turmaDao.findByEscolaCursoSerieAnoSemestre(escola, curso, serie, ano, periodo);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public List<Turma> findAbertasEFechadasByEscolaCursoSerie(Escola escola, Curso curso, Serie serie) {

        verifyArgument("escola", escola, Escola.class);
        verifyArgument("curso", curso, Curso.class);
        verifyArgument("serie", serie, Serie.class);

        Set<Turma> turmas = new HashSet<Turma>();

        turmas.addAll(findByEscolaCursoSerieEStatus(escola, curso, serie, StatusTurma.ABERTA));
        turmas.addAll(findByEscolaCursoSerieEStatus(escola, curso, serie, StatusTurma.FECHADA));

        return new ArrayList<Turma>(turmas);

    }

    @Override
    public List<Turma> findByExample(Turma entity) {

        verifyArgument("entity", entity, Turma.class);

        try {

            return turmaDao.findByExample(entity);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void remover(Turma entity) {

        verifyArgument("entity", entity, Turma.class);

        entity = turmaDao.attach(entity);
        turmaDao.remove(entity);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void alterar(Turma entity) {

        verifyArgument("entity", entity, Turma.class);

        turmaDao.merge(entity);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inserir(Turma entity) {

        verifyArgument("entity", entity, Turma.class);

        turmaDao.save(entity);

    }

    @Override
    public boolean existsEntityWithSameUniqueKeyAttributes(Turma entity) {

        verifyArgument(entity, Turma.class);

        try {

            return (null != turmaDao.existsEntityWithSameUniqueKeyAttributes(entity)) ? true : false;

        } catch (EmptyResultDataAccessException ex) {

            return false;

        }

    }
    
}
