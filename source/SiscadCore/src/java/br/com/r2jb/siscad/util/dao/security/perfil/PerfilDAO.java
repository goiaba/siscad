/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.util.dao.security.perfil;

import br.com.r2jb.siscad.dao.base.BaseDAO;
import br.com.r2jb.siscad.util.entity.security.Perfil;
import br.com.r2jb.siscad.util.entity.security.TipoPerfil;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface PerfilDAO extends BaseDAO<Perfil> {

    Perfil findByTipoPerfil(TipoPerfil tipoPerfil);

}
