/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.professor;

import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.dao.base.BaseDAO;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface ProfessorDAO extends BaseDAO<Professor> {

    Professor findByCpf(String cpf);

    Professor findByMatricula(int iMatricula);

    List<Professor> findByNome(String nome);

    List<Professor> findByNome(String nome, Boolean ativo);

    Long getProximaMatricula();

}
