/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.util.dao.security.recurso.impl;

import br.com.r2jb.siscad.dao.base.impl.BaseDAOImpl;
import br.com.r2jb.siscad.util.dao.security.recurso.RecursoDAO;
import br.com.r2jb.siscad.util.entity.security.Perfil;
import br.com.r2jb.siscad.util.entity.security.Recurso;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Repository("RecursoDAO")
public class RecursoDAOImpl extends BaseDAOImpl<Recurso> implements RecursoDAO {

    @Override
    public List<Recurso> findUnsafeResources() {

        return getEntityManager()
                .createQuery("SELECT r FROM Recurso r WHERE r.seguro = :seg")
                .setParameter("seg", Boolean.FALSE)
                .getResultList();

    }

    @Override
    public List<Recurso> findRecursosByPerfil(Perfil perfil) {

        return getEntityManager()
                .createQuery("SELECT r FROM Recurso r JOIN r.lstPerfilRecurso lpr WHERE lpr.perfil = :perfil")
                .setParameter("perfil", perfil)
                .getResultList();

    }

}
