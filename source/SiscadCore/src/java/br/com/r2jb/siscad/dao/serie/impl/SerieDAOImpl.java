/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.serie.impl;

import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.Serie;
import br.com.r2jb.siscad.dao.base.impl.BaseDAOImpl;
import br.com.r2jb.siscad.dao.serie.SerieDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Repository("SerieDAO")
public class SerieDAOImpl extends BaseDAOImpl<Serie> implements SerieDAO {

    @Override
    public List<Serie> findSeriePorEscolaECurso(Escola escola, Curso curso) {

        String query = "SELECT DISTINCT s" +
                       " FROM Escola e JOIN e.listaEscolaCurso ec JOIN ec.curso c JOIN c.listaSeries s" +
                       " WHERE e = :escola" +
                         " AND c = :curso";

        return getEntityManager()
                .createQuery(query)
                .setParameter("escola", escola)
                .setParameter("curso",curso)
                .getResultList();

    }

    @Override
    public List<Serie> findByEscola(Escola escola) {

        String query = "SELECT s" +
                       " FROM Escola e JOIN e.listaEscolaCurso ec JOIN ec.curso c JOIN c.listaSeries s" +
                       " WHERE e = :escola";

        return getEntityManager()
                .createQuery(query)
                .setParameter("escola", escola)
                .getResultList();

    }

    @Override
    public List<Serie> findSeriePorCurso(Curso curso) {

        String query = "SELECT s" +
                       " FROM Serie s " +
                       " WHERE s.curso = :curso " +
                       " ORDER BY s.ordem";

        return getEntityManager()
                .createQuery(query, Serie.class)
                .setParameter("curso", curso)
                .getResultList();

    }

}
