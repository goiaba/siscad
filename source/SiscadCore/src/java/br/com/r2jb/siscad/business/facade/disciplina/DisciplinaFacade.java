/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.disciplina;

import br.com.r2jb.siscad.business.entity.Disciplina;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.entity.Turma;
import br.com.r2jb.siscad.business.exception.EntidadePossuiRelacionamentoException;
import br.com.r2jb.siscad.business.facade.base.BaseFacade;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface DisciplinaFacade extends BaseFacade<Disciplina> {

    List<Disciplina> findByTurma(Turma turma);

    List<Disciplina> findByProfessorETurma(Professor professor, Turma turma);

    boolean existsEntityWithSameUniqueKeyAttributes(Disciplina entity);

    void inserir(Disciplina entity);

    void alterar(Disciplina entity);

    void remover(Disciplina entity) throws EntidadePossuiRelacionamentoException;

    List<Disciplina> findByExample(Disciplina entity);

}