/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.util.facade.security.perfil.impl;

import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.util.dao.security.perfil.PerfilDAO;
import br.com.r2jb.siscad.util.entity.security.Perfil;
import br.com.r2jb.siscad.util.entity.security.TipoPerfil;
import br.com.r2jb.siscad.util.facade.security.perfil.PerfilFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Service
public class PerfilFacadeImpl extends BaseFacadeImpl<Perfil, PerfilDAO> implements PerfilFacade {

    @Autowired PerfilDAO perfilDao;

    @Override
    public Perfil findByTipoPerfil(TipoPerfil tipoPerfil) {

        verifyArgument("tipoPerfil", tipoPerfil, TipoPerfil.class);

        try {

            return perfilDao.findByTipoPerfil(tipoPerfil);

        } catch (EmptyResultDataAccessException ex) {

            return null;

        }

    }

}
