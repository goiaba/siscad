/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.periodoAvaliacao.impl;

import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.PeriodoAvaliacao;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.util.Ano;
import br.com.r2jb.siscad.business.util.Periodo;
import br.com.r2jb.siscad.dao.base.impl.BaseDAOImpl;
import br.com.r2jb.siscad.dao.periodoAvaliacao.PeriodoAvaliacaoDAO;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Repository("PeriodoAvaliacaoDAO")
public class PeriodoAvaliacaoDAOImpl extends BaseDAOImpl<PeriodoAvaliacao> implements PeriodoAvaliacaoDAO {

    @Override
    public List<Ano> findAnosPorProfessor(Professor professor) {

        String query = "SELECT DISTINCT pa.ano" +
                       " FROM Professor p JOIN p.listaAlocacao a JOIN a.turma t JOIN t.serie s JOIN s.curso c JOIN c.listaPeriodoAvaliacao pa" +
                       " WHERE p = :prof";

        return getEntityManager()
                .createQuery(query, Ano.class)
                .setParameter("prof", professor)
                .getResultList();

    }

    @Override
    public List<Periodo> findSemestresPorProfessor(Professor professor) {

        String query = "SELECT DISTINCT pa.semestre" +
                       " FROM Professor p JOIN p.listaAlocacao a JOIN a.turma t JOIN t.serie s JOIN s.curso c JOIN c.listaPeriodoAvaliacao pa" +
                       " WHERE p = :prof";

        return getEntityManager()
                .createQuery(query, Periodo.class)
                .setParameter("prof", professor)
                .getResultList();
        
    }

    @Override
    public List<PeriodoAvaliacao> findByCursoPeriodoEAno(Curso curso, Periodo periodo, Ano ano) {

        String query = "SELECT pa" +
                       " FROM PeriodoAvaliacao pa" +
                       " WHERE pa.curso = :curso" +
                       "   AND pa.semestre = :periodo" +
                       "   AND pa.ano = :ano";

        return getEntityManager()
                .createQuery(query)
                .setParameter("curso", curso)
                .setParameter("periodo", periodo)
                .setParameter("ano", ano)
                .getResultList();

    }

    @Override
    public List<PeriodoAvaliacao> findByCurso(Curso curso, boolean anoAtualOuMaisRecente) {

        Ano ano = null;

        if (anoAtualOuMaisRecente) {

            ano = Ano.getAnoAtual();

        }

        String query = "SELECT pa" +
                       " FROM PeriodoAvaliacao pa" +
                       " WHERE pa.curso = :curso" +
                       "   AND (:ano IS NULL OR pa.ano >= :ano)";

        return getEntityManager()
                .createQuery(query)
                .setParameter("curso", curso)
                .setParameter("ano", ano)
                .getResultList();


    }

    @Override
    public List<Periodo> findSemestres(Professor professor, Escola escola, Curso curso, Ano ano) {

        if (null == professor && null == escola && null == curso && null == ano) {

            return getEntityManager()
                    .createQuery("SELECT DISTINCT pa.semestre FROM PeriodoAvaliacao pa", Periodo.class)
                    .getResultList();
            
        }

        boolean first = true;

        StringBuilder query = new StringBuilder("SELECT DISTINCT pa.semestre")
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

        if (null != curso) {

            query.append((first) ? " WHERE c = :curso" : " AND c = :curso");
            first = false;

        }

        if (null != ano) {

            query.append((first) ? " WHERE pa.ano = :ano" : " AND pa.ano = :ano");

        }

        TypedQuery typedQuery = getEntityManager()
                    .createQuery(query.toString(), Periodo.class);

        if (null != professor) {

            typedQuery.setParameter("professor", professor);

        }

        if (null != escola) {

            typedQuery.setParameter("escola", escola);

        }

        if (null != curso) {

            typedQuery.setParameter("curso", curso);

        }

        if (null != ano) {

            typedQuery.setParameter("ano", ano);

        }

        return typedQuery.getResultList();

    }

    @Override
    public List<Ano> findAnos(Professor professor, Escola escola, Curso curso, Periodo semestre) {

        if (null == professor && null == escola && null == curso && null == semestre) {

            return getEntityManager()
                    .createQuery("SELECT DISTINCT pa.ano FROM PeriodoAvaliacao pa", Ano.class)
                    .getResultList();

        }

        boolean first = true;

        StringBuilder query = new StringBuilder("SELECT DISTINCT pa.ano")
                                        .append(" FROM Professor p JOIN p.listaAlocacao a JOIN a.turma turma JOIN turma.escola e JOIN e.listaEscolaCurso ec JOIN ec.curso c JOIN c.listaPeriodoAvaliacao pa");


        if (null != professor) {

            query.append(" WHERE p = :professor");
            first = false;

        }

        if (null != escola) {

            query.append((first) ? " WHERE e = :escola" : " AND e = :escola");
            first = false;

        }

        if (null != curso) {

            query.append((first) ? " WHERE c = :curso" : " AND c = :curso");
            first = false;

        }

        if (null != semestre) {

            query.append((first) ? " WHERE pa.semestre = :semestre" : " AND pa.semestre = :semestre");

        }

        TypedQuery typedQuery = getEntityManager()
                    .createQuery(query.toString(), Ano.class);

        if (null != professor) {

            typedQuery.setParameter("professor", professor);

        }

        if (null != escola) {

            typedQuery.setParameter("escola", escola);

        }

        if (null != curso) {

            typedQuery.setParameter("curso", curso);

        }

        if (null != semestre) {

            typedQuery.setParameter("semestre", semestre);

        }

        return typedQuery.getResultList();

    }

    @Override
    public PeriodoAvaliacao existsEntityWithSameUniqueKeyAttributes(PeriodoAvaliacao entity) {

        String query = "SELECT perAval" +
                       " FROM PeriodoAvaliacao perAval" +
                       " WHERE perAval <> :perAval" +
                       "   AND perAval.semestre = :periodo" +
                       "   AND perAval.abreviacao = :abreviacao";

        return getEntityManager()
                .createQuery(query, PeriodoAvaliacao.class)
                .setParameter("perAval", entity)
                .setParameter("periodo", entity.getSemestre())
                .setParameter("abreviacao", entity.getAbreviacao())
                .getSingleResult();

    }

    @Override
    public List<PeriodoAvaliacao> findByExample(PeriodoAvaliacao entity) {

        String query = "SELECT perAval" +
                       " FROM PeriodoAvaliacao perAval " +
                       " WHERE lower(perAval.descricao) like :descricao" +
                       "   AND lower(perAval.abreviacao) like :abreviacao";

        if (null != entity.getSemestre()) {
            query += "   AND (perAval.semestre = :periodo)";
        }
        
        if (null != entity.getAno()) {
            query += "   AND (perAval.ano = :ano)";
        }
        
        if (null != entity.getCurso()) {
            query += "   AND (perAval.curso = :curso)";
        }
        
        query += " ORDER BY perAval.curso.descricao, perAval.semestre, perAval.ano, perAval.descricao";

        Query q = getEntityManager()
                  .createQuery(query)
                  .setParameter("descricao", (null != entity.getDescricao()) ? entity.getDescricao().toLowerCase() + "%" : null)
                  .setParameter("abreviacao", (null != entity.getAbreviacao()) ? entity.getAbreviacao().toLowerCase() + "%" : null);

        if (null != entity.getSemestre()) {
            q.setParameter("periodo", entity.getSemestre());
        }
        
        if (null != entity.getAno()) {
            q.setParameter("ano", entity.getAno());
        }
        
        if (null != entity.getCurso()) {
            q.setParameter("curso", entity.getCurso());
        }

        return q.getResultList();

    }

}
