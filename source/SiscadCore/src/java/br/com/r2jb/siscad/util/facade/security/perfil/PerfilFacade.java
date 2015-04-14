/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.util.facade.security.perfil;

import br.com.r2jb.siscad.business.facade.base.BaseFacade;
import br.com.r2jb.siscad.util.entity.security.Perfil;
import br.com.r2jb.siscad.util.entity.security.TipoPerfil;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface PerfilFacade extends BaseFacade<Perfil> {

    Perfil findByTipoPerfil(TipoPerfil tipoPerfil);

}
