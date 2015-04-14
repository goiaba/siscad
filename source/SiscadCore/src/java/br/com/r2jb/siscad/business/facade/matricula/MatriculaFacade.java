/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.matricula;

import br.com.r2jb.siscad.business.entity.Aluno;
import br.com.r2jb.siscad.business.entity.Matricula;
import br.com.r2jb.siscad.business.entity.Turma;
import br.com.r2jb.siscad.business.facade.base.BaseFacade;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface MatriculaFacade extends BaseFacade<Matricula> {

    void registrarMatricula(Matricula matricula);

    Matricula findMatriculaPorAlunoETurma(Aluno aluno, Turma turma);

    Matricula findMatriculaMaisRecenteByAluno(Aluno aluno);

    void alterarMatricula(Matricula matricula);
    

}
