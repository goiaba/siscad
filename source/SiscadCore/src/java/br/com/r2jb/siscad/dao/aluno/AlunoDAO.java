/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.dao.aluno;

import br.com.r2jb.siscad.business.entity.Aluno;
import br.com.r2jb.siscad.business.entity.Turma;
import br.com.r2jb.siscad.dao.base.BaseDAO;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface AlunoDAO extends BaseDAO<Aluno> {

    Aluno findByRA(Integer ra);

    List<Aluno> findByNome(String nome);

    List<Aluno> findByNome(String nome, Boolean ativo);

    Long getProximoRA();

    List<Aluno> listAll();

    List<Aluno> findAlunosPorTurma(Turma turma);

}
