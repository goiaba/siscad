/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.notasaluno;

import br.com.r2jb.siscad.business.entity.NotasAluno;
import br.com.r2jb.siscad.business.facade.base.BaseFacade;
import java.util.List;

/**
 *
 * @author Joaquim
 */
public interface NotasAlunoFacade extends BaseFacade<NotasAluno> {

    List <NotasAluno> findNotasPorAluno(int raAluno);

}
