/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.util.dao.security.usuario.impl;

import br.com.r2jb.siscad.dao.base.impl.BaseDAOImpl;
import br.com.r2jb.siscad.util.dao.security.usuario.UsuarioDAO;
import br.com.r2jb.siscad.util.entity.security.TipoPerfil;
import br.com.r2jb.siscad.util.entity.security.Usuario;
import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Repository("UsuarioDAO")
public class UsuarioDAOImpl extends BaseDAOImpl<Usuario> implements UsuarioDAO {

    @Override
    public Usuario findByUsername(String username) {

        return getEntityManager()
                .createQuery("SELECT u FROM Usuario u WHERE u.usuario = :username AND u.ativo = :ativo", Usuario.class)
                .setParameter("username", username)
                .setParameter("ativo", Boolean.TRUE)
                .getSingleResult();
        
    }

    @Override
    public List<Usuario> findAllUsersExceptAluno() {

        return getEntityManager()
                .createQuery("SELECT u FROM Usuario u WHERE u.aluno IS NULL")
                .getResultList();

    }

    @Override
    public Usuario findByCpf(String cpf) {

        return getEntityManager()
                .createQuery("SELECT u FROM Usuario u WHERE u.pessoa.cpf = :cpf", Usuario.class)
                .setParameter("cpf", cpf)
                .getSingleResult();

    }

    @Override
    public List<Usuario> findByNomeAndTipoPerfil(String nome, TipoPerfil tipoPerfil, Boolean ativo) {

        StringBuilder query = new StringBuilder();

        query.append("SELECT u")
             .append(" FROM Usuario u")
             .append(" WHERE lower(u.pessoa.nome) like :nome");

        if (null != ativo) {
             query.append(" AND u.ativo = :ativo");
        }

        if (null != tipoPerfil) {
            query.append(" AND u.perfil.tipoPerfil = :perfil");
        } else {
            query.append(" AND u.perfil.tipoPerfil in :perfis");
        }

        TypedQuery typedQuery = getEntityManager().createQuery(query.toString(), Usuario.class);
        
        typedQuery.setParameter("nome", nome.toLowerCase() + "%");

        if (null != ativo) {
            typedQuery.setParameter("ativo", ativo);
        }

        if (null != tipoPerfil) {
            typedQuery.setParameter("perfil", tipoPerfil);
        } else {
            typedQuery.setParameter("perfis", TipoPerfil.perfisMenosAlunoProfessorEResponsavel());
        }

        return typedQuery.getResultList();

    }

}
