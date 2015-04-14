/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.curso.impl;


import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.business.facade.curso.CursoFacade;
import br.com.r2jb.siscad.business.util.Ano;
import br.com.r2jb.siscad.business.util.Periodo;
import br.com.r2jb.siscad.dao.curso.CursoDAO;
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
@Service("CursoFacade")
public class CursoFacadeImpl extends BaseFacadeImpl<Curso, CursoDAO> implements CursoFacade {

    @Autowired private CursoDAO cursoDAO;

    @Override
    @Transactional(readOnly=true)
    public List<Curso> findByProfessorEEscola(Professor professor, Escola escola) {

        verifyArgument(professor, Professor.class);
        verifyArgument(escola, Escola.class);

        try {

            return cursoDAO.findCursosPorProfessorEEscola(professor, escola);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    @Transactional(readOnly=true)
    public List<Curso> findByProfessor(Professor professor) {

        verifyArgument(professor, Professor.class);

        try {

            return cursoDAO.findCursosPorProfessor(professor);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    @Transactional(readOnly=true)
    public List<Curso> findByEscola(Escola escola) {

        verifyArgument(escola, Escola.class);

        try {

            return cursoDAO.findCursosPorEscola(escola);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }


    @Override
    @Transactional(readOnly=true)
    public List<Curso> findByProfessorEscolaSemestreEAno(Professor professor, Escola escola, Periodo semestre, Ano ano) {

        try {

            return cursoDAO.findCursos(professor, escola, semestre, ano);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    @Transactional(readOnly=true)
    public List<Curso> findByEscolaSemestreEAno(Escola escola, Periodo semestre, Ano ano) {

        try {

            return cursoDAO.findCursos(null, escola, semestre, ano);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void inserir(Curso curso) {

        verifyArgument(curso, Curso.class);

        cursoDAO.save(curso);

    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void alterar(Curso curso) {

        verifyArgument(curso, Curso.class);

        cursoDAO.merge(curso);
        
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void remover(Curso curso) {

        verifyArgument(curso, Curso.class);

        curso = cursoDAO.attach(curso);
        cursoDAO.remove(curso);

    }

    /**
     * @param cursoDAO the cursoDAO to set
     */
    public void setCursoDAO(CursoDAO cursoDAO) {
        this.cursoDAO = cursoDAO;
    }

}