/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.matricula.impl;

import br.com.r2jb.siscad.business.entity.Aluno;
import br.com.r2jb.siscad.business.entity.Matricula;
import br.com.r2jb.siscad.business.entity.Turma;
import br.com.r2jb.siscad.dao.base.impl.BaseDAOImpl;
import br.com.r2jb.siscad.dao.matricula.MatriculaDAO;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Repository("MatriculaDAO")
public class MatriculaDAOImpl extends BaseDAOImpl<Matricula> implements MatriculaDAO {

    @Override
    public Matricula findMatriculaPorAlunoETurma(Aluno aluno, Turma turma) {

        String query = "SELECT matricula" +
                       " FROM Matricula matricula JOIN matricula.aluno a JOIN matricula.turma t" +
                       " WHERE a = :aluno" +
                       "  AND t = :turma";

        return getEntityManager()
                .createQuery(query, Matricula.class)
                .setParameter("aluno", aluno)
                .setParameter("turma", turma)
                .getSingleResult();

    }

    @Override
    public Matricula findMatriculaMaisRecenteByAluno(Aluno aluno) {

        String query = "SELECT matricula" +
                       " FROM Matricula matricula" +
                       " WHERE matricula.aluno = :aluno" +
                       "   AND matricula.dataentrada = (SELECT max(m.dataentrada)" +
                       "                                  FROM Matricula m" +
                       "                                 WHERE m.aluno = :aluno)";

        return getEntityManager()
                .createQuery(query, Matricula.class)
                .setParameter("aluno", aluno)
                .getSingleResult();

    }

}
