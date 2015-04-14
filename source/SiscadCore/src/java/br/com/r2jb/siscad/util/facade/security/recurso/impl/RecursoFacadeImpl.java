/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.util.facade.security.recurso.impl;

import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.util.dao.security.recurso.RecursoDAO;
import br.com.r2jb.siscad.util.entity.security.Perfil;
import br.com.r2jb.siscad.util.entity.security.Recurso;
import br.com.r2jb.siscad.util.facade.security.recurso.RecursoFacade;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Service("RecursoFacade")
public class RecursoFacadeImpl extends BaseFacadeImpl<Recurso, RecursoDAO> implements RecursoFacade {

    @Autowired RecursoDAO recursoDAO;

    @Override
    public List<Recurso> findUnsafeResources() {

        try {

            return recursoDAO.findUnsafeResources();

        } catch (EmptyResultDataAccessException e) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public List<Recurso> findRecursosByPerfil(Perfil perfil) {

        verifyArgument("perfil", perfil, Perfil.class);

        try {

            return recursoDAO.findRecursosByPerfil(perfil);

        } catch (EmptyResultDataAccessException e) {

            return Collections.EMPTY_LIST;

        }

    }

}
