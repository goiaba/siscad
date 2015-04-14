/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.business.exception.SenhaFracaException;
import br.com.r2jb.siscad.business.exception.SenhasNaoConferemException;
import br.com.r2jb.siscad.business.util.EstadoCivil;
import br.com.r2jb.siscad.util.entity.security.Usuario;
import br.com.r2jb.siscad.util.facade.security.usuario.UsuarioFacade;
import br.com.r2jb.siscad.util.security.SiscadSecurity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@ManagedBean
@RequestScoped
public class AlteracaoDadosUsuario extends BaseBean {

    private UsuarioFacade usuarioFacade = ServiceLocator.getFacade(UsuarioFacade.class);
    private SiscadSecurity siscadSecurity = ServiceLocator.security();
    private Usuario usuario;

    private String mode;

    private String senhaAtual;
    private String novaSenha;
    private String confirmacaoSenha;

    @PostConstruct
    public void init() {

        usuario = (Usuario) getSession().getAttribute(LoginBean.SESSION_USER_KEY);

        if (null == usuario) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "ADU.init");

        }
        
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String update() {

        try {

            if ("d".equals(mode)) {

                usuarioFacade.alterar(usuario);

            } else if ("p".equals(mode)) {

                siscadSecurity.changePassword(usuario, senhaAtual, novaSenha);

            } 

            addFacesMessage("alteradoSucesso");
            return "/principal.jsf";

        } catch (SenhaFracaException e) {

            addFacesMessage("senhaFraca");
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "ADU.update.SenhaFracaException", e);
            return null;

        } catch (SenhasNaoConferemException e) {

            addFacesMessage("senhasNaoConferem");
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "ADU.update.SenhasNaoConferemException", e);
            return null;
            
        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "ADU.update.Exception", e);
            return null;

        }

    }

    public String back() {

        return "/principal.jsf";
        
    }

    public List<SelectItem> getListaEstadoCivil() {

        List<SelectItem> itens = new ArrayList<SelectItem>();

        for (EstadoCivil ec : EstadoCivil.values()) {

            itens.add(new SelectItem(ec, ec.getDescricao(getLocale())));

        }

        return itens;

    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

}
