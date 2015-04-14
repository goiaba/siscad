/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.avaliacao.impl;

import br.com.r2jb.siscad.business.entity.Avaliacao;
import br.com.r2jb.siscad.business.entity.Disciplina;
import br.com.r2jb.siscad.dao.avaliacao.AvaliacaoDAO;
import br.com.r2jb.siscad.dao.base.impl.BaseDAOImpl;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Repository("AvaliacaoDAO")
public class AvaliacaoDAOImpl extends BaseDAOImpl<Avaliacao> implements AvaliacaoDAO {

    @Override
    public List<Avaliacao> findAvaliacoesPorDisciplina(Disciplina disciplina) {

        String query = "SELECT avaliacao" +
                       " FROM Disciplina d JOIN d.listaAvaliacao avaliacao" +
                       " WHERE d = :disciplina";

        return getEntityManager()
                .createQuery(query)
                .setParameter("disciplina", disciplina)
                .getResultList();

    }

    @Override
    public Avaliacao existsEntityWithSameUniqueKeyAttributes(Avaliacao entity) {

        String query = "SELECT avaliacao" +
                       " FROM Avaliacao avaliacao" +
                       " WHERE avaliacao <> :avaliacao" +
                       "   AND avaliacao.periodoAvaliacao = :periodoAvaliacao" +
                       "   AND avaliacao.abreviacao = :abreviacao";

        return getEntityManager()
                .createQuery(query, Avaliacao.class)
                .setParameter("avaliacao", entity)
                .setParameter("periodoAvaliacao", entity.getPeriodoAvaliacao())
                .setParameter("abreviacao", entity.getAbreviacao())
                .getSingleResult();
        
    }

    @Override
    public List<Avaliacao> findByExample(Avaliacao entity) {

        String query = "SELECT avaliacao" +
                       " FROM Avaliacao avaliacao " +
                       " WHERE lower(avaliacao.descricao) like :descricao" +
                       "   AND lower(avaliacao.abreviacao) like :abreviacao";

        if (null != entity.getPeriodoAvaliacao()) {
            query += "   AND (avaliacao.periodoAvaliacao = :periodoAvaliacao)";
        }
        
        query += " ORDER BY avaliacao.periodoAvaliacao.descricao, avaliacao.periodoAvaliacao.semestre, avaliacao.periodoAvaliacao.ano, avaliacao.descricao";

        Query q = getEntityManager()
                  .createQuery(query)
                  .setParameter("descricao", (null != entity.getDescricao()) ? entity.getDescricao().toLowerCase() + "%" : null)
                  .setParameter("abreviacao", (null != entity.getAbreviacao()) ? entity.getAbreviacao().toLowerCase() + "%" : null);

        if (null != entity.getPeriodoAvaliacao()) {
            q.setParameter("periodoAvaliacao", entity.getPeriodoAvaliacao());
        }

        return q.getResultList();

    }

}
