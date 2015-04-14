/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.util.security.impl;


import br.com.r2jb.siscad.business.exception.ArgumentoNuloException;
import br.com.r2jb.siscad.business.exception.SenhaFracaException;
import br.com.r2jb.siscad.business.exception.SenhasNaoConferemException;
import br.com.r2jb.siscad.util.appconfig.ApplicationConfig;
import br.com.r2jb.siscad.util.entity.security.Perfil;
import br.com.r2jb.siscad.util.entity.security.Recurso;
import br.com.r2jb.siscad.util.security.SiscadSecurity;
import br.com.r2jb.siscad.util.entity.security.Usuario;
import br.com.r2jb.siscad.util.facade.security.recurso.RecursoFacade;
import br.com.r2jb.siscad.util.facade.security.usuario.UsuarioFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import org.apache.commons.lang.RandomStringUtils;
import org.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Service("SiscadSecurity")
public class SiscadSecurityBasic implements SiscadSecurity {

    @Autowired private UsuarioFacade usuarioFacade;
    @Autowired private RecursoFacade recursoFacade;
    
    private List<Recurso> unsafeResources = new ArrayList<Recurso>();

    @PostConstruct
    public void init() {

        unsafeResources.addAll(recursoFacade.findUnsafeResources());

    }

    public SiscadSecurityBasic() {

    }

    @Override
    public List<Recurso> getUnsafePages() {

        return unsafeResources;

    }

    @Override
    public boolean isUnsafeResource(String sRequestedPage) {

        verifyArgument("sRequestedPage", sRequestedPage, String.class);

        for (Recurso r : unsafeResources) {

            if (sRequestedPage.equals(r.getRecurso())) {

                return true;

            }

        }

        return false;

    }

    @Override
    public boolean isAuthorizedAccess(Usuario usuario, String sRequestedPage) {

        verifyArgument("usuario", usuario, Usuario.class);
        verifyArgument("sRequestedPage", sRequestedPage, String.class);

        Perfil perfil = usuario.getPerfil();

        for (Recurso recurso : recursoFacade.findRecursosByPerfil(perfil)) {

            if (recurso.getRecurso().equals(sRequestedPage)) {
                
                return true;
                
            }

        }

        return false;

    }

    @Override
    public Usuario tryLogin(String username, String senha) {

        Usuario usuario = usuarioFacade.findByUsername(username);

        if (null != usuario && usuario.getAtivo()) {

            return BCrypt.checkpw(senha, usuario.getSenha()) ? usuario : null;

        }

        return null;

    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void changePassword(Usuario usuario, String oldPasswd, String newPasswd) throws SenhasNaoConferemException, SenhaFracaException {

        verifyArgument("usuario", usuario, String.class);
        verifyArgument("newPasswd", newPasswd, String.class);
        verifyArgument("oldPasswd", oldPasswd, String.class);

        validatePassord(newPasswd);

        if (BCrypt.checkpw(oldPasswd, usuario.getSenha())) {

            usuario.setSenha(BCrypt.hashpw(newPasswd, BCrypt.gensalt()));
            usuarioFacade.alterar(usuario);

        } else {

            throw new SenhasNaoConferemException();

        }

    }

    @Override
    public void validatePassord(String passwd) throws SenhaFracaException {

        if (null != passwd) {

            if (passwd.length() < 5) {

                throw new SenhaFracaException();

            }

        }

    }

    @Override
    public String generateRandomPassword() {

        Integer passwdLength = ApplicationConfig.getInstance().getIntegerProperty("RandomPasswordLength");

        return RandomStringUtils.randomAlphanumeric(passwdLength);

    }

    @Override
    public void sendPassword(String nome, String email, String username, String senha, Locale locale) {

        System.out.println("Nome: " + nome + " - Email: " + email + " - Usuário: " + username + " - Senha: " + senha);

    }

    @Override
    public void generateAndSendRandomPassword(String nome, String email, String username, Locale locale) {

        String senha = generateRandomPassword();

        sendPassword(nome, email, username, senha, locale);

    }

    private void verifyArgument(String argumentName, Object argumentValue, Class argumentType) {

        if (null == argumentValue) {

            throw new ArgumentoNuloException(argumentName, argumentType);

        }

    }
    
    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

}
