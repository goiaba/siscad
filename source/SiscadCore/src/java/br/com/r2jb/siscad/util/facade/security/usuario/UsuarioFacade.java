/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.util.facade.security.usuario;

import br.com.r2jb.siscad.business.entity.Aluno;
import br.com.r2jb.siscad.business.entity.Pessoa;
import br.com.r2jb.siscad.business.facade.base.BaseFacade;
import br.com.r2jb.siscad.util.entity.security.TipoPerfil;
import br.com.r2jb.siscad.util.entity.security.Usuario;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface UsuarioFacade extends BaseFacade<Usuario> {

    Usuario findByUsername(String username);

    List<Usuario> findAllUsersExceptAluno();

    Usuario findByCpf(String cpf);

    List<Usuario> findByNomeAndTipoPerfil(String nome, TipoPerfil tipoPerfil, Boolean ativo);

    List<Usuario> findByNomeAndTipoPerfil(String searchTerm, TipoPerfil tipoPerfil);

    boolean usuarioExists(String cpf);

    void criaUsuarioParaPessoa(Pessoa pessoa, TipoPerfil perfil, Locale locale);

    void criaUsuarioParaPessoa(Usuario usuario);
    
    void criaUsuarioParaAluno(Aluno aluno, Locale locale);

    void updateDataUltimoAcesso(Usuario usuario);

    void alterar(Usuario usuario);

    void remover(Usuario entity);

}
