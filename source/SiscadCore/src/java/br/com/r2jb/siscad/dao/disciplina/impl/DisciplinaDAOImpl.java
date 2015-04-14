/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.disciplina.impl;

import br.com.r2jb.siscad.business.entity.Disciplina;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.entity.Turma;
import br.com.r2jb.siscad.dao.base.impl.BaseDAOImpl;
import br.com.r2jb.siscad.dao.disciplina.DisciplinaDAO;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Repository("DisciplinaDAO")
public class DisciplinaDAOImpl extends BaseDAOImpl<Disciplina> implements DisciplinaDAO {

    @Override
    public List<Disciplina> findDisciplinasPorProfessorETurma(Professor professor, Turma turma) {

        String query = "SELECT disciplina" +
                       " FROM Professor p JOIN p.listaAlocacao a JOIN a.turma t JOIN a.disciplina disciplina" +
                       " WHERE p = :prof" +
                       "  AND t = :turma";

        return getEntityManager()
                .createQuery(query)
                .setParameter("prof", professor)
                .setParameter("turma", turma)
                .getResultList();

    }

    @Override
    public List<Disciplina> findDisciplinasPorTurma(Turma turma) {

        String query = "SELECT disciplina" +
                       " FROM Turma t JOIN t.serie s JOIN s.listaMatrizCurricular mc JOIN mc.listaMatrizCurricularDisciplina mcd JOIN mcd.disciplina disciplina" +
                       " WHERE t = :turma";

        return getEntityManager()
                .createQuery(query)
                .setParameter("turma", turma)
                .getResultList();

    }

    @Override
    public List<Disciplina> findByExample(Disciplina entity) {

        String query = "SELECT disciplina" +
                       " FROM Disciplina disciplina " +
                       " WHERE lower(disciplina.nome) like :nome" +
                       "   AND lower(disciplina.descricao) like :descricao" +
                       "   AND lower(disciplina.abreviacao) like :abreviacao";

        Query q = getEntityManager()
                  .createQuery(query)
                  .setParameter("nome", (null != entity.getNome()) ? entity.getNome().toLowerCase() + "%" : null)
                  .setParameter("descricao", (null != entity.getDescricao()) ? entity.getDescricao().toLowerCase() + "%" : null)
                  .setParameter("abreviacao", (null != entity.getAbreviacao()) ? entity.getAbreviacao().toLowerCase() + "%" : null);

        return q.getResultList();

    }

    @Override
    public Disciplina existsEntityWithSameUniqueKeyAttributes(Disciplina entity) {

        String query = "SELECT disciplina" +
                       " FROM Disciplina disciplina" +
                       " WHERE disciplina <> :disciplina" +
                       "   AND disciplina.abreviacao = :abreviacao";

        return getEntityManager()
                .createQuery(query, Disciplina.class)
                .setParameter("disciplina", entity)
                .setParameter("abreviacao", entity.getAbreviacao())
                .getSingleResult();
        
    }

}