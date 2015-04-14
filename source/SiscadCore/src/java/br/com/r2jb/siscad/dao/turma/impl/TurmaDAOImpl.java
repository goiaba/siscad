/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.turma.impl;

import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.entity.Serie;
import br.com.r2jb.siscad.business.entity.Turma;
import br.com.r2jb.siscad.business.entity.Turno;
import br.com.r2jb.siscad.business.util.Ano;
import br.com.r2jb.siscad.business.util.Periodo;
import br.com.r2jb.siscad.business.util.StatusTurma;
import br.com.r2jb.siscad.dao.base.impl.BaseDAOImpl;
import br.com.r2jb.siscad.dao.turma.TurmaDAO;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Repository("TurmaDAO")
public class TurmaDAOImpl extends BaseDAOImpl<Turma> implements TurmaDAO {

    @Override
    public List<Turma> findTurmasPorProfessorEscolaCursoETurno(Professor professor, Escola escola, Curso curso, Turno turno) {

        String query = "SELECT DISTINCT turma" +
                       " FROM Professor p JOIN p.listaAlocacao a JOIN a.turma turma JOIN turma.turno t JOIN turma.escola e JOIN e.listaEscolaCurso ec JOIN ec.curso c" +
                       " WHERE p = :prof" +
                       "  AND e = :escola" +
                       "  AND c = :curso" +
                       "  AND t = :turno";

        return getEntityManager()
                .createQuery(query)
                .setParameter("prof", professor)
                .setParameter("escola", escola)
                .setParameter("curso", curso)
                .setParameter("turno", turno)
                .getResultList();

    }

    @Override
    public List<Turma> findByExample(Turma turma) {

        String query = "SELECT turma" +
                       " FROM Turma turma" +
                       " WHERE (:descricao IS NULL OR lower(turma.descricao) like :descricao)" +
                       "   AND (:codigo IS NULL OR lower(turma.codigo) = :codigo)" +
                       "   AND (:escola IS NULL OR turma.escola = :escola)" +
                       "   AND (:serie IS NULL OR turma.serie = :serie)" +
                       "   AND (:turno IS NULL OR turma.turno = :turno)";

        if (null != turma.getPeriodo()) {
            query += "   AND (turma.periodo = :periodo)";
        }

        if (null != turma.getAno()) {
            query += "   AND (turma.ano = :ano)";
        }

        if (null != turma.getStatus()) {
            query += "   AND (turma.status = :status)";
        }

        if ("".equals(turma.getDescricao())) {
            turma.setDescricao(null);
        }

        if ("".equals(turma.getCodigo())) {
            turma.setCodigo(null);
        }

        Query q = getEntityManager()
                    .createQuery(query)
                    .setParameter("descricao", (null != turma.getDescricao()) ? "%" + turma.getDescricao().toLowerCase() + "%" : null)
                    .setParameter("codigo", (null != turma.getCodigo()) ? turma.getCodigo().toLowerCase() : null)
                    .setParameter("escola", turma.getEscola())
                    .setParameter("serie", turma.getSerie())
                    .setParameter("turno", turma.getTurno());

        if (null != turma.getPeriodo()) {
            q.setParameter("periodo", turma.getPeriodo());
        }

        if (null != turma.getAno()) {
            q.setParameter("ano", turma.getAno());
        }

        if (null != turma.getStatus()) {
            q.setParameter("status", turma.getStatus());
        }

        return q.getResultList();
        
    }

    @Override
    public List<Turma> findTurmasPorProfessorEscolaECurso(Professor professor, Escola escola, Curso curso) {

        String query = "SELECT DISTINCT turma" +
                       " FROM Professor p JOIN p.listaAlocacao a JOIN a.turma turma JOIN turma.escola e JOIN e.listaEscolaCurso ec JOIN ec.curso c" +
                       " WHERE p = :prof" +
                       "  AND e = :escola" +
                       "  AND c = :curso";

        return getEntityManager()
                .createQuery(query)
                .setParameter("prof", professor)
                .setParameter("escola", escola)
                .setParameter("curso", curso)
                .getResultList();

    }

    @Override
    public List<Turma> findTurmas(Professor professor, Escola escola, Curso curso, Periodo periodo, Ano ano) {

        if (null == professor && null == escola && null == curso && null == periodo && null == ano) {

            return getEntityManager()
                    .createQuery("SELECT turma FROM Turma turma", Turma.class)
                    .getResultList();

        }

        boolean first = true;

        StringBuilder query = new StringBuilder("SELECT DISTINCT turma")
                                        .append(" FROM Professor p JOIN" + 
                                                    " p.listaAlocacao a JOIN" +
                                                    " a.turma turma JOIN" +
                                                    " turma.escola e JOIN" +
                                                    " e.listaEscolaCurso ec JOIN" +
                                                    " ec.curso c JOIN" +
                                                    " c.listaPeriodoAvaliacao pa")
                                        .append(" WHERE");


        if (null != professor) {

            query.append(" p = :professor");
            first = false;

        }

        if (null != escola) {

            query.append((first) ? " e = :escola" : " AND e = :escola");
            first = false;

        }

        if (null != curso) {

            query.append((first) ? " c = :curso" : " AND c = :curso");
            first = false;

        }

        if (null != periodo) {

            query.append((first) ? " pa.semestre = :periodo AND turma.periodo = :periodo" : " AND pa.semestre = :periodo AND turma.periodo = :periodo");
            first = false;

        }

        if (null != ano) {

            query.append((first) ? " pa.ano = :ano AND turma.ano = :ano" : " AND pa.ano = :ano AND turma.ano = :ano");

        }

        TypedQuery typedQuery = getEntityManager()
                    .createQuery(query.toString(), Turma.class);

        if (null != professor) {

            typedQuery.setParameter("professor", professor);

        }

        if (null != escola) {

            typedQuery.setParameter("escola", escola);

        }

        if (null != curso) {

            typedQuery.setParameter("curso", curso);

        }

        if (null != periodo) {

            typedQuery.setParameter("periodo", periodo);

        }

        if (null != ano) {

            typedQuery.setParameter("ano", ano);

        }

        return typedQuery.getResultList();

    }

    @Override
    public List<Turma> findTurmasAtivasPorEscola(Escola escola) {

        String query = "SELECT turma" +
                       " FROM Escola escola JOIN escola.listaTurma turma" +
                       " WHERE escola = :escola" +
                       "       AND (turma.status = :statusAberta" +
                       "            OR turma.status = :statusFechada)";

        return getEntityManager()
                .createQuery(query)
                .setParameter("statusAberta", StatusTurma.ABERTA)
                .setParameter("statusFechada", StatusTurma.FECHADA)
                .setParameter("escola", escola)
                .getResultList();
    }

    @Override
    public List<Turma> findTurmasPorEscola(Escola escola) {

        String query = "SELECT turma" +
                       " FROM Escola escola JOIN escola.listaTurma turma" +
                       " WHERE escola = :escola";

        return getEntityManager()
                .createQuery(query)
                .setParameter("escola", escola)
                .getResultList();
    }

    @Override
    public List<Turma> findTurmasPorEscolaCursoSerie(Escola escola, Curso curso, Serie serie) {

        String query = "SELECT DISTINCT t" +
                       " FROM Turma t JOIN t.serie s JOIN s.curso c JOIN c.listaEscolaCurso ec JOIN ec.escola e" +
                       " WHERE s = :serie" +
                       "  AND e = :escola" +
                       "  AND c = :curso";

        return getEntityManager()
                .createQuery(query)
                .setParameter("escola", escola)
                .setParameter("curso", curso)
                .setParameter("serie", serie)
                .getResultList();
    }

    @Override
    public List<Turma> findTurmasPorEscolaCursoSerieEStatus(Escola escola, Curso curso, Serie serie, StatusTurma status) {

        String query = "SELECT DISTINCT t" +
                       " FROM Turma t JOIN t.serie s JOIN s.curso c JOIN c.listaEscolaCurso ec JOIN ec.escola e" +
                       " WHERE t.status = :status" +
                       "  AND s = :serie" +
                       "  AND e = :escola" +
                       "  AND c = :curso";

        return getEntityManager()
                .createQuery(query)
                .setParameter("status", status)
                .setParameter("escola", escola)
                .setParameter("curso", curso)
                .setParameter("serie", serie)
                .getResultList();
    }

    @Override
    public List<Turma> findByEscolaCursoSerieAnoSemestre(Escola escola, Curso curso, Serie serie, Ano ano, Periodo periodo) {

        String query = "SELECT DISTINCT t" +
                       " FROM Turma t JOIN t.serie s JOIN s.curso c JOIN c.listaEscolaCurso ec JOIN ec.escola e" +
                       " WHERE t.ano = :ano" +
                       "  AND t.periodo = :periodo" +
                       "  AND s = :serie" +
                       "  AND e = :escola" +
                       "  AND c = :curso";

        return getEntityManager()
                .createQuery(query)
                .setParameter("ano", ano)
                .setParameter("periodo", periodo)
                .setParameter("escola", escola)
                .setParameter("curso", curso)
                .setParameter("serie", serie)
                .getResultList();

    }

    @Override
    public Turma existsEntityWithSameUniqueKeyAttributes(Turma entity) {

        String query = "SELECT turma" +
                       " FROM Turma turma" +
                       " WHERE turma <> :turma" +
                       "   AND turma.descricao = :descricao" +
                       "   AND turma.codigo = :codigo" +
                       "   AND turma.periodo = :periodo" +
                       "   AND turma.ano = :ano";

        return getEntityManager()
                .createQuery(query, Turma.class)
                .setParameter("turma", entity)
                .setParameter("descricao", entity.getDescricao())
                .setParameter("codigo", entity.getCodigo())
                .setParameter("periodo", entity.getPeriodo())
                .setParameter("ano", entity.getAno())
                .getSingleResult();

    }

}
