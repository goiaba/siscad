/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.notasaluno.impl;

import br.com.r2jb.siscad.business.entity.NotasAluno;
import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.business.facade.notasaluno.NotasAlunoFacade;
import br.com.r2jb.siscad.dao.notasaluno.NotasAlunoDAO;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joaquim
 */
@Service("NotasAlunoFacade")
public class NotasAlunoFacadeImpl extends BaseFacadeImpl<NotasAluno, NotasAlunoDAO> implements NotasAlunoFacade {

    @Autowired
    private NotasAlunoDAO notasAlunoDao;

    @Override
    public List<NotasAluno> findNotasPorAluno(int raAluno) {

        try {

            return notasAlunoDao.findNotasPorAluno(raAluno);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }
    }

}
