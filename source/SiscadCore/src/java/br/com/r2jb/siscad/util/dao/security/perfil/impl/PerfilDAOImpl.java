/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.util.dao.security.perfil.impl;

import br.com.r2jb.siscad.dao.base.impl.BaseDAOImpl;
import br.com.r2jb.siscad.util.dao.security.perfil.PerfilDAO;
import br.com.r2jb.siscad.util.entity.security.Perfil;
import br.com.r2jb.siscad.util.entity.security.TipoPerfil;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Repository
public class PerfilDAOImpl extends BaseDAOImpl<Perfil> implements PerfilDAO {

    @Override
    public Perfil findByTipoPerfil(TipoPerfil tipoPerfil) {

        return getEntityManager()
                .createQuery("SELECT p FROM Perfil p WHERE p.tipoPerfil = :tipoPerfil", Perfil.class)
                .setParameter("tipoPerfil", tipoPerfil)
                .getSingleResult();

    }

}
