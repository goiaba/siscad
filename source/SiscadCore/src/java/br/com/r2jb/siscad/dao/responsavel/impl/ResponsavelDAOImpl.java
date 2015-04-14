/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.responsavel.impl;

import br.com.r2jb.siscad.business.entity.Responsavel;
import br.com.r2jb.siscad.dao.base.impl.BaseDAOImpl;
import br.com.r2jb.siscad.dao.responsavel.ResponsavelDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Repository("ResponsavelDAO")
public class ResponsavelDAOImpl extends BaseDAOImpl<Responsavel> implements ResponsavelDAO {

    @Override
    public Responsavel findByCpf(String cpf) {

        return getEntityManager()
                .createQuery("SELECT r FROM Responsavel r WHERE r.pessoa.cpf = :cpf", Responsavel.class)
                .setParameter("cpf", cpf)
                .getSingleResult();

    }

    @Override
    public List<Responsavel> findByNome(String nome) {

        return getEntityManager()
                .createQuery("SELECT r FROM Responsavel r WHERE lower(r.pessoa.nome) like :nome", Responsavel.class)
                .setParameter("nome", "%" + nome.toLowerCase() + "%")
                .getResultList();

    }


    @Override
    public List<Responsavel> findByNome(String nome, Boolean ativo) {

        return getEntityManager()
                .createQuery("SELECT r FROM Responsavel r WHERE lower(r.pessoa.nome) like :nome AND r.ativo = :ativo", Responsavel.class)
                .setParameter("nome", "%" + nome.toLowerCase() + "%")
                .setParameter("ativo", ativo)
                .getResultList();

    }

}
