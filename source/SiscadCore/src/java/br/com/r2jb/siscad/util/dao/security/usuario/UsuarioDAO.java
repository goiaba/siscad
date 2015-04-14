/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.util.dao.security.usuario;

import br.com.r2jb.siscad.dao.base.BaseDAO;
import br.com.r2jb.siscad.util.entity.security.TipoPerfil;
import br.com.r2jb.siscad.util.entity.security.Usuario;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface UsuarioDAO extends BaseDAO<Usuario> {

    Usuario findByUsername(String username);

    List<Usuario> findAllUsersExceptAluno();

    Usuario findByCpf(String cpf);

    List<Usuario> findByNomeAndTipoPerfil(String nome, TipoPerfil tipoPerfil, Boolean ativo);

}
