/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.nota.impl;

import br.com.r2jb.siscad.business.entity.Avaliacao;
import br.com.r2jb.siscad.business.entity.Disciplina;
import br.com.r2jb.siscad.business.entity.Matricula;
import br.com.r2jb.siscad.business.entity.Nota;
import br.com.r2jb.siscad.dao.base.impl.BaseDAOImpl;
import br.com.r2jb.siscad.dao.nota.NotaDAO;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Repository("NotaDAO")
public class NotaDAOImpl extends BaseDAOImpl<Nota> implements NotaDAO {

    @Override
    public Float isNotaJaRegistrada(Matricula matricula, Disciplina disciplina, Avaliacao avaliacao) {

        String query = "SELECT nota.valor" +
                       " FROM Nota nota" +
                       " WHERE nota.matricula = :matricula" +
                       "  AND nota.disciplina = :disciplina" +
                       "  AND nota.avaliacao = :avaliacao";

        return getEntityManager()
                .createQuery(query, Float.class)
                .setParameter("matricula", matricula)
                .setParameter("disciplina", disciplina)
                .setParameter("avaliacao", avaliacao)
                .getSingleResult();

    }

    @Override
    public Nota findByDisciplinaAvaliacaoEMatricula(Disciplina disciplina, Avaliacao avaliacao, Matricula matricula) {

        String query = "SELECT nota" +
                       " FROM Nota nota" +
                       " WHERE nota.disciplina = :disciplina" +
                       "  AND nota.avaliacao = :avaliacao" +
                       "  AND nota.matricula = :matricula";

        return getEntityManager()
                .createQuery(query, Nota.class)
                .setParameter("disciplina", disciplina)
                .setParameter("avaliacao", avaliacao)
                .setParameter("matricula", matricula)
                .getSingleResult();


    }
    
}
