/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.curso.impl;

import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.util.Ano;
import br.com.r2jb.siscad.business.util.Periodo;
import br.com.r2jb.siscad.dao.base.impl.BaseDAOImpl;
import br.com.r2jb.siscad.dao.curso.CursoDAO;
import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Repository("CursoDAO")
public class CursoDAOImpl extends BaseDAOImpl<Curso> implements CursoDAO {

    @Override
    public List<Curso> findCursosPorProfessorEEscola(Professor professor, Escola escola) {

        String query = "SELECT DISTINCT c" +
                       " FROM Professor p JOIN p.listaAlocacao a JOIN a.turma t JOIN t.serie s JOIN s.curso c JOIN c.listaEscolaCurso ec JOIN ec.escola e" +
                       " WHERE p = :prof" +
                         " AND e = :escola";

        return getEntityManager()
                .createQuery(query)
                .setParameter("prof", professor)
                .setParameter("escola",escola)
                .getResultList();

    }

    @Override
    public List<Curso> findCursosPorProfessor(Professor professor) {

        String query = "SELECT DISTINCT c" +
                       " FROM Professor p JOIN p.listaAlocacao a JOIN a.turma t JOIN t.serie s JOIN s.curso c" +
                       " WHERE p = :prof";

        return getEntityManager()
                .createQuery(query)
                .setParameter("prof", professor)
                .getResultList();

    }

    @Override
    public List<Curso> findCursosPorEscola(Escola escola) {

        String query = "SELECT c" +
                       " FROM Escola e JOIN e.listaEscolaCurso ec JOIN ec.curso c" +
                       " WHERE e = :escola";

        return getEntityManager()
                .createQuery(query)
                .setParameter("escola", escola)
                .getResultList();

    }

    @Override
    public List<Curso> findCursos(Professor professor, Escola escola, Periodo semestre, Ano ano) {

        if (null == professor && null == escola && null == semestre && null == ano) {

            return getEntityManager()
                    .createQuery("SELECT DISTINCT c FROM Curso c", Curso.class)
                    .getResultList();

        }

        boolean first = true;

        StringBuilder query = new StringBuilder("SELECT DISTINCT c")
                                        .append(" FROM Professor p JOIN p.listaAlocacao a JOIN a.turma turma JOIN turma.escola e JOIN e.listaEscolaCurso ec JOIN ec.curso c JOIN c.listaPeriodoAvaliacao pa");


        if (null != professor) {

            query.append(" WHERE")
                 .append(" p = :professor");
            first = false;

        }

        if (null != escola) {

            query.append((first) ? " WHERE e = :escola" : " AND e = :escola");
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
                    .createQuery(query.toString(), Curso.class);

        if (null != professor) {

            typedQuery.setParameter("professor", professor);

        }

        if (null != escola) {

            typedQuery.setParameter("escola", escola);

        }

        if (null != semestre) {

            typedQuery.setParameter("semestre", semestre);

        }

        if (null != ano) {

            typedQuery.setParameter("ano", ano);

        }

        return typedQuery.getResultList();

    }

}
