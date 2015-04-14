/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.matricula;

import br.com.r2jb.siscad.business.entity.Aluno;
import br.com.r2jb.siscad.business.entity.Matricula;
import br.com.r2jb.siscad.business.entity.Turma;
import br.com.r2jb.siscad.dao.base.BaseDAO;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface MatriculaDAO extends BaseDAO<Matricula> {

    Matricula findMatriculaPorAlunoETurma(Aluno aluno, Turma turma);

    Matricula findMatriculaMaisRecenteByAluno(Aluno aluno);

}
