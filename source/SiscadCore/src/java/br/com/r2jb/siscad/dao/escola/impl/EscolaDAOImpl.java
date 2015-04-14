/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.dao.escola.impl;

import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.util.Ano;
import br.com.r2jb.siscad.business.util.Periodo;
import br.com.r2jb.siscad.dao.base.impl.BaseDAOImpl;
import br.com.r2jb.siscad.dao.escola.EscolaDAO;
import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Repository("EscolaDAO")
public class EscolaDAOImpl extends BaseDAOImpl<Escola> implements EscolaDAO {

    @Override
    public Escola findByNome(String nomeEscola) {

        return getEntityManager()
                .createNamedQuery("Escola.findByNome", Escola.class)
                .setParameter("nome", nomeEscola)
                .getSingleResult();

    }

    @Override
    public List<Escola> findEscolasPorProfessor(Professor professor) {

        String query = "SELECT DISTINCT e" +
                       " FROM Professor p JOIN p.listaAlocacao a JOIN a.turma t JOIN t.escola e" +
                       " WHERE p = :prof";

        return getEntityManager()
                .createQuery(query)
                .setParameter("prof", professor)
                .getResultList();

    }

    @Override
    public List<Escola> findEscolas(Professor professor, Curso curso, Periodo semestre, Ano ano) {

        if (null == professor && null == curso && null == semestre && null == ano) {
            
            return getEntityManager()
                    .createQuery("SELECT DISTINCT e FROM Escola e", Escola.class)
                    .getResultList();
            
        }

        boolean first = true;

        StringBuilder query = new StringBuilder("SELECT DISTINCT e")
                                        .append(" FROM Professor p JOIN p.listaAlocacao a JOIN a.turma turma JOIN turma.escola e JOIN e.listaEscolaCurso ec JOIN ec.curso c JOIN c.listaPeriodoAvaliacao pa");


        if (null != professor) {

            query.append(" WHERE")
                 .append(" p = :professor");
            first = false;

        }

        if (null != curso) {

            query.append((first) ? " WHERE c = :curso" : " AND c = :curso");
            first = false;

        }

        if (null != semestre) {

            query.append((first) ? " WHERE pa.semestre = :semestre" : " AND pa.semestre = :semestre");
            first = false;

        }

        if (null != ano) {

            query.append((first) ? " WHERE pa.ano = :ano" : " AND pa.ano = :ano");

        }

        TypedQuery typedQuery = getEntityManager()
                    .createQuery(query.toString(), Escola.class);

        if (null != professor) {

            typedQuery.setParameter("professor", professor);

        }

        if (null != curso) {

            typedQuery.setParameter("curso", curso);

        }

        if (null != semestre) {

            typedQuery.setParameter("semestre", semestre);

        }

        if (null != ano) {

            typedQuery.setParameter("ano", ano);

        }

        return typedQuery.getResultList();

    }

    @Override
    public Escola findByCnpj(String cnpj) {

        return getEntityManager()
                .createQuery("SELECT e FROM Escola e WHERE e.cnpj = :cnpj", Escola.class)
                .setParameter("cnpj", cnpj)
                .getSingleResult();

    }

}
