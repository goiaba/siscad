/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.disciplina;

import br.com.r2jb.siscad.business.entity.Disciplina;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.entity.Turma;
import br.com.r2jb.siscad.dao.base.BaseDAO;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface DisciplinaDAO extends BaseDAO<Disciplina> {

    List<Disciplina> findDisciplinasPorProfessorETurma(Professor professor, Turma turma);

    List<Disciplina> findDisciplinasPorTurma(Turma turma);

    List<Disciplina> findByExample(Disciplina entity);

}