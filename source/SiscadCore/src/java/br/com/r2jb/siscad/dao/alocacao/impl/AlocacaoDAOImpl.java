/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.alocacao.impl;

import br.com.r2jb.siscad.business.entity.Alocacao;
import br.com.r2jb.siscad.dao.alocacao.AlocacaoDAO;
import br.com.r2jb.siscad.dao.base.impl.BaseDAOImpl;
import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Repository("AlocacaoDAO")
public class AlocacaoDAOImpl extends BaseDAOImpl<Alocacao> implements AlocacaoDAO {

    @Override
    public List<Alocacao> findByExample(Alocacao entity) {

        boolean firstWhere = true;

        String query = "SELECT alocacao" +
                       " FROM Alocacao alocacao";

        if (null != entity.getProfessor()) {
            query += firstWhere ? " WHERE alocacao.professor = :professor" : " AND alocacao.professor = :professor";
            firstWhere = false;
        }

        if (null != entity.getDisciplina()) {
            query += firstWhere ? " WHERE alocacao.disciplina = :disciplina" : " AND alocacao.disciplina = :disciplina";
            firstWhere = false;
        }

        if (null != entity.getTurma()) {
            query += firstWhere ? " WHERE alocacao.turma = :turma" : " AND alocacao.turma = :turma";
        }

        query += " ORDER BY alocacao.turma.descricao, alocacao.disciplina.nome, alocacao.professor.pessoa.nome";

        TypedQuery<Alocacao> tQuery = getEntityManager()
                                        .createQuery(query, Alocacao.class);

        if (null != entity.getProfessor()) {
            tQuery.setParameter("professor", entity.getProfessor());
        }

        if (null != entity.getDisciplina()) {
            tQuery.setParameter("disciplina", entity.getDisciplina());
        }

        if (null != entity.getTurma()) {
            tQuery.setParameter("turma", entity.getTurma());
        }

        return tQuery.getResultList();

    }

    @Override
    public Alocacao existsEntityWithSameUniqueKeyAttributes(Alocacao entity) {

        String query = "SELECT alocacao" +
                       " FROM Alocacao alocacao" +
                       " WHERE alocacao <> :alocacao" +
                       "   AND alocacao.turma = :turma" +
                       "   AND alocacao.disciplina = :disciplina" +
                       "   AND alocacao.professor = :professor";

        return getEntityManager()
                .createQuery(query, Alocacao.class)
                .setParameter("alocacao", entity)
                .setParameter("turma", entity.getTurma())
                .setParameter("disciplina", entity.getDisciplina())
                .setParameter("professor", entity.getProfessor())
                .getSingleResult();

    }

}
