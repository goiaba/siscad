/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.professor.impl;

import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.dao.base.impl.BaseDAOImpl;
import br.com.r2jb.siscad.dao.professor.ProfessorDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Repository("ProfessorDAO")
public class ProfessorDAOImpl extends BaseDAOImpl<Professor> implements ProfessorDAO {

    @Override
    public Professor findByCpf(String cpf) {

        return getEntityManager()
                .createQuery("SELECT p FROM Professor p WHERE p.pessoa.cpf = :cpf", Professor.class)
                .setParameter("cpf", cpf)
                .getSingleResult();

    }

    @Override
    public List<Professor> findByNome(String nome) {

        return getEntityManager()
                .createQuery("SELECT p FROM Professor p WHERE lower(p.pessoa.nome) like :nome", Professor.class)
                .setParameter("nome", "%" + nome.toLowerCase() + "%")
                .getResultList();

    }


    @Override
    public List<Professor> findByNome(String nome, Boolean ativo) {

        return getEntityManager()
                .createQuery("SELECT p FROM Professor p WHERE lower(p.pessoa.nome) like :nome AND p.ativo = :ativo", Professor.class)
                .setParameter("nome", "%" + nome.toLowerCase() + "%")
                .setParameter("ativo", ativo)
                .getResultList();

    }

    @Override
    public Professor findByMatricula(int iMatricula) {

        return getEntityManager()
                .createQuery("SELECT p FROM Professor p WHERE p.matricula = :matricula", Professor.class)
                .setParameter("matricula", iMatricula)
                .getSingleResult();

    }

    @Override
    public Long getProximaMatricula() {

        return (Long) getEntityManager()
                .createNativeQuery("SELECT nextval(\'professor_matricula_seq\'::regclass)")
                .getSingleResult();

    }

}
