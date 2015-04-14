/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.util.facade.security.recurso;

import br.com.r2jb.siscad.business.facade.base.BaseFacade;
import br.com.r2jb.siscad.util.entity.security.Perfil;
import br.com.r2jb.siscad.util.entity.security.Recurso;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface RecursoFacade extends BaseFacade<Recurso> {

    List<Recurso> findUnsafeResources();

    List<Recurso> findRecursosByPerfil(Perfil perfil);

}
