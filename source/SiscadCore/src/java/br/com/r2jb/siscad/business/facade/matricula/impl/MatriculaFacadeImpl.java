/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.business.facade.matricula.impl;


import br.com.r2jb.siscad.business.entity.Aluno;
import br.com.r2jb.siscad.business.entity.Matricula;
import br.com.r2jb.siscad.business.entity.Turma;
import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.business.facade.matricula.MatriculaFacade;
import br.com.r2jb.siscad.business.util.SituacaoMatricula;
import br.com.r2jb.siscad.dao.matricula.MatriculaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Service("MatriculaFacade")
public class MatriculaFacadeImpl extends BaseFacadeImpl<Matricula, MatriculaDAO> implements MatriculaFacade {

    @Autowired private MatriculaDAO matriculaDAO;

    @Override
    public Matricula findMatriculaPorAlunoETurma(Aluno aluno, Turma turma) {

        verifyArgument(aluno, Aluno.class);
        verifyArgument(turma, Turma.class);
        
        try {

            return matriculaDAO.findMatriculaPorAlunoETurma(aluno, turma);

        } catch (EmptyResultDataAccessException ex) {

            return null;

        }

    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void registrarMatricula(Matricula matricula) {

        verifyArgument("matricula", matricula, Matricula.class);
        
        matricula.setSituacaoMatricula(SituacaoMatricula.MATRICULADO);

        matriculaDAO.save(matricula);

    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void alterarMatricula(Matricula matricula) {

        verifyArgument("matricula", matricula, Matricula.class);

        matriculaDAO.merge(matricula);

    }

    @Override
    public Matricula findMatriculaMaisRecenteByAluno(Aluno aluno) {

        verifyArgument("aluno", aluno, Aluno.class);

        try {

            return matriculaDAO.findMatriculaMaisRecenteByAluno(aluno);

        } catch (EmptyResultDataAccessException ex) {

            return null;

        }

    }

    public void setMatriculaDAO(MatriculaDAO matriculaDAO) {
        this.matriculaDAO = matriculaDAO;
    }
        
}
