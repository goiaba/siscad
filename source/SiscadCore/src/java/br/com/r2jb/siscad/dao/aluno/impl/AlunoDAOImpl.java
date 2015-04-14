package br.com.r2jb.siscad.dao.aluno.impl;

import br.com.r2jb.siscad.business.entity.Aluno;
import br.com.r2jb.siscad.business.entity.Turma;
import br.com.r2jb.siscad.dao.aluno.AlunoDAO;
import br.com.r2jb.siscad.dao.base.impl.BaseDAOImpl;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Repository("AlunoDAO")
public class AlunoDAOImpl extends BaseDAOImpl<Aluno> implements AlunoDAO {

    @Override
    public Aluno findByRA(Integer ra) {

        return getEntityManager()
                    .createNamedQuery("Aluno.findByRa", Aluno.class)
                    .setParameter("ra", ra)
                    .getSingleResult();
        
    }

    @Override
    public List<Aluno> findByNome(String nome) {

        return getEntityManager()
                .createQuery("SELECT a FROM Aluno a WHERE lower(a.nome) like :nome", Aluno.class)
                .setParameter("nome", "%" + nome.toLowerCase() + "%")
                .getResultList();

    }

    @Override
    public List<Aluno> findByNome(String nome, Boolean ativo) {

        return getEntityManager()
                .createQuery("SELECT a FROM Aluno a WHERE lower(a.nome) like :nome AND a.ativo = :ativo", Aluno.class)
                .setParameter("nome", "%" + nome.toLowerCase() + "%")
                .setParameter("ativo", ativo)
                .getResultList();

    }

    @Override
    public Long getProximoRA() {

        return (Long) getEntityManager()
                .createNativeQuery("SELECT nextval(\'aluno_ra_seq\'::regclass)")
                .getSingleResult();

    }

    @Override
    public List<Aluno> listAll() {

        return getEntityManager()
                .createNamedQuery("Aluno.findAll")
                .getResultList();

    }

    @Override
    public List<Aluno> findAlunosPorTurma(Turma turma) {

        String query = "SELECT aluno" +
                       " FROM Turma turma JOIN turma.listaMatricula m JOIN m.aluno aluno" +
                       " WHERE turma = :turma";

        return getEntityManager()
                .createQuery(query)
                .setParameter("turma", turma)
                .getResultList();

    }

}
