/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.util.i18n.ResourceBundleUtils;
import br.com.r2jb.siscad.util.security.SiscadSecurity;
import br.com.r2jb.siscad.util.entity.security.Usuario;
import br.com.r2jb.siscad.util.facade.security.usuario.UsuarioFacade;
import javax.faces.event.ActionEvent;
import java.util.Date;
import java.text.Format;
import java.text.SimpleDateFormat;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@ManagedBean
@SessionScoped
public class LoginBean extends BaseBean {

    public static final String SESSION_USER_KEY = "sessionUser";
    
    public static final String HOME_PAGE_WITH_REDIRECT = "/principal?faces-redirect=true";
    public static final String LOGIN_PAGE_WITH_REDIRECT = "/pages/login/login?faces-redirect=true";

    transient private SiscadSecurity siscadSecurity = ServiceLocator.security();
    transient private UsuarioFacade usuarioFacade = ServiceLocator.getFacade(UsuarioFacade.class);
    
    private String username;
    private String senha;

    private String welcomeMessage = "";

    private Locale localeEmUso;

    private Usuario usuario = null;

    @PreDestroy
    public void cleanUp() throws Exception {
        //Não remover o método.
    }

    public void onChooseLocale(ActionEvent event) {

        String selectedLocale = event.getComponent().getId();

        setLocale(selectedLocale);

        setLocaleEmUso(getLocale());

    }

    public String login() {

        try {
            
            usuario = siscadSecurity.tryLogin(getUsername(), getSenha());

            if (null != usuario) {

                setLocale(usuario.getLocalePadrao());
                setLocaleEmUso(getLocale());

                setWelcomeMessage(usuario.getPessoa().getNome(), usuario.getDataUltimoAcesso());

                getSession().setAttribute(SESSION_USER_KEY, usuario);
                usuarioFacade.updateDataUltimoAcesso(usuario);

                return HOME_PAGE_WITH_REDIRECT;
                
            } else {

                addFacesMessage("ui.login.usuarioSenhaIncorretos");

            }

            return null;
            
        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "LB.login", e);
            
            return null;

        }
        
    }

    public String logout() {

        getSession().removeAttribute(SESSION_USER_KEY);
        getSession().invalidate();
        usuario = null;

        return LOGIN_PAGE_WITH_REDIRECT;

    }

    public void setWelcomeMessage(String sNome, Date dDate) {

        if (null != dDate && null != sNome) {
            
            Format fmtHour = new SimpleDateFormat("HH:mm");
            Format fmtDate = new SimpleDateFormat("dd/MM/yyyy");

            this.welcomeMessage = ResourceBundleUtils.getMessage(getLocaleEmUso(), "welcomeMessage", sNome, fmtHour.format(dDate), fmtDate.format(dDate));

        }

    }

    public String getWelcomeMessage() {

        return welcomeMessage;


    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the locale
     */
    public Locale getLocaleEmUso() {
        return localeEmUso;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocaleEmUso(Locale locale) {
        this.localeEmUso = locale;
    }

}
