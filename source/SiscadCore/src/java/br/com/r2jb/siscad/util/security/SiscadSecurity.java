/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.util.security;

import br.com.r2jb.siscad.business.exception.SenhaFracaException;
import br.com.r2jb.siscad.business.exception.SenhasNaoConferemException;
import br.com.r2jb.siscad.util.entity.security.Recurso;
import br.com.r2jb.siscad.util.entity.security.Usuario;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface SiscadSecurity extends Serializable {

    List<Recurso> getUnsafePages();

    boolean isUnsafeResource(String sRequestedPage);

    boolean isAuthorizedAccess(Usuario usuario, String sRequestedPage);

    Usuario tryLogin(String username, String senha);

    void changePassword(Usuario usuario, String oldPasswd, String newPasswd) throws SenhasNaoConferemException, SenhaFracaException;

    void validatePassord(String passwd) throws SenhaFracaException;

    String generateRandomPassword();

    void sendPassword(String nome, String email, String username, String senha, Locale locale);

    void generateAndSendRandomPassword(String nome, String email, String username, Locale locale);

}
