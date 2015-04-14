/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.serie.impl;

import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.Serie;
import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.business.facade.serie.SerieFacade;
import br.com.r2jb.siscad.dao.serie.SerieDAO;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Service("SerieFacade")
public class SerieFacadeImpl extends BaseFacadeImpl<Serie, SerieDAO> implements SerieFacade {

    @Autowired private SerieDAO serieDAO;

    @Override
    public List<Serie> findSeriePorEscolaECurso(Escola escola, Curso curso) {
        verifyArgument("escola", escola, Escola.class);
        verifyArgument("curso", curso, Curso.class);

        try {

            return serieDAO.findSeriePorEscolaECurso(escola, curso);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }
    }

    @Override
    public List<Serie> findByEscola(Escola escola) {

        verifyArgument("escola", escola, Escola.class);

        try {

            return serieDAO.findByEscola(escola);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void inserir(Serie serie) {

        verifyArgument(serie, Serie.class);

        serieDAO.save(serie);

    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void alterar(Serie serie) {

        verifyArgument(serie, Serie.class);

        serieDAO.merge(serie);

    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void remover(Serie serie) {

        verifyArgument(serie, Serie.class);

        serie = serieDAO.attach(serie);
        serieDAO.remove(serie);

    }

    @Override
    public List<Serie> findSeriePorCurso(Curso curso) {
        return serieDAO.findSeriePorCurso(curso);
    }

}
