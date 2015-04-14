/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.notasaluno;

import br.com.r2jb.siscad.business.entity.NotasAluno;
import br.com.r2jb.siscad.dao.base.BaseDAO;
import java.util.List;

/**
 *
 * @author Joaquim
 */
public interface NotasAlunoDAO extends BaseDAO<NotasAluno> {

    List <NotasAluno> findNotasPorAluno(int raAluno);

}
