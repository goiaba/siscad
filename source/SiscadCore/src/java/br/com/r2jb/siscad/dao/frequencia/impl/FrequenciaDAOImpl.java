/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.frequencia.impl;

import br.com.r2jb.siscad.business.entity.Disciplina;
import br.com.r2jb.siscad.business.entity.Matricula;
import br.com.r2jb.siscad.business.entity.Frequencia;
import br.com.r2jb.siscad.business.entity.PeriodoAvaliacao;
import br.com.r2jb.siscad.dao.base.impl.BaseDAOImpl;
import br.com.r2jb.siscad.dao.frequencia.FrequenciaDAO;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Repository("FrequenciaDAO")
public class FrequenciaDAOImpl extends BaseDAOImpl<Frequencia> implements FrequenciaDAO {

    @Override
    public Float isFrequenciaJaRegistrada(Matricula matricula, Disciplina disciplina, PeriodoAvaliacao periodoAvaliacao) {

        String query = "SELECT frequencia.valor" +
                       " FROM Frequencia frequencia" +
                       " WHERE frequencia.matricula = :matricula" +
                       "  AND frequencia.disciplina = :disciplina" +
                       "  AND frequencia.periodoAvaliacao = :periodoAvaliacao";

        return getEntityManager()
                .createQuery(query, Float.class)
                .setParameter("matricula", matricula)
                .setParameter("disciplina", disciplina)
                .setParameter("periodoAvaliacao", periodoAvaliacao)
                .getSingleResult();

    }

    @Override
    public Frequencia findByDisciplinaAvaliacaoEMatricula(Disciplina disciplina, PeriodoAvaliacao periodoAvaliacao, Matricula matricula) {

        String query = "SELECT frequencia" +
                       " FROM Frequencia frequencia" +
                       " WHERE frequencia.disciplina = :disciplina" +
                       "  AND frequencia.periodoAvaliacao = :periodoAvaliacao" +
                       "  AND frequencia.matricula = :matricula";

        return getEntityManager()
                .createQuery(query, Frequencia.class)
                .setParameter("disciplina", disciplina)
                .setParameter("periodoAvaliacao", periodoAvaliacao)
                .setParameter("matricula", matricula)
                .getSingleResult();


    }
    
}
