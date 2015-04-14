/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.util.dao.security.recurso;

import br.com.r2jb.siscad.dao.base.BaseDAO;
import br.com.r2jb.siscad.util.entity.security.Perfil;
import br.com.r2jb.siscad.util.entity.security.Recurso;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface RecursoDAO extends BaseDAO<Recurso> {

    List<Recurso> findUnsafeResources();

    List<Recurso> findRecursosByPerfil(Perfil perfil);

}
