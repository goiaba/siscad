/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.business.entity.Pessoa;
import br.com.r2jb.siscad.business.util.EstadoCivil;
import br.com.r2jb.siscad.util.entity.security.Perfil;
import br.com.r2jb.siscad.util.entity.security.TipoPerfil;
import br.com.r2jb.siscad.util.entity.security.Usuario;
import br.com.r2jb.siscad.util.facade.security.usuario.UsuarioFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@ManagedBean
@ViewScoped
public class CrudUsuarioBean extends AbstractCrudBean<Usuario> {

    private Boolean cpfReadOnly;

    private TipoPerfil perfilBusca;
    private String confirmacaoSenha;
    private Locale defaultUserLocale = getDefaultLocale();

    private UsuarioFacade usuarioFacade = ServiceLocator.getFacade(UsuarioFacade.class);

    @PostConstruct
    public void postConstruct() {

        resetBean();

    }

    public List<SelectItem> getPerfis() {

        List<SelectItem> itens = new ArrayList<SelectItem>();

        for (TipoPerfil tp : TipoPerfil.values()) {

            if (!TipoPerfil.ALUNO.equals(tp) &&
                !TipoPerfil.PROFESSOR.equals(tp) &&
                !TipoPerfil.RESPONSAVEL.equals(tp)) {

                itens.add(new SelectItem(tp, tp.getDescricao(getLocale())));

            }

        }

        return itens;

    }

    public void validarSenhas(FacesContext context, UIComponent toValidate, Object value) {

        String s = (String) ((UIInput) toValidate).getSubmittedValue();

        if ((getUpdateMode() && StringUtils.isEmpty(s)) || (StringUtils.isNotEmpty(s) && s.equals(getEntity().getSenha()))) {

            ((UIInput) toValidate).setValid(true);

        } else {

            ((UIInput) toValidate).setValid(false);

            addFacesMessage(toValidate.getClientId(context), "senhasNaoConferem", FacesMessage.SEVERITY_ERROR);

        }

    }

    public void validarCpf(FacesContext context, UIComponent toValidate, Object value) {

        try {

            String cpf = (String) ((UIInput) toValidate).getSubmittedValue();

            if (usuarioFacade.usuarioExists(cpf)) {

                ((UIInput) toValidate).setValid(false);

                addFacesMessage(toValidate.getClientId(context), "cpfExistente", FacesMessage.SEVERITY_ERROR);

            } else {

                ((UIInput) toValidate).setValid(true);

            }

        } catch (Exception e) {

            ((UIInput) toValidate).setValid(false);
            addFacesMessage(((UIInput) toValidate).getClientId(), "erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CUB.validarCpf.Exception", e);

        }

    }

    public List<SelectItem> getListaEstadoCivil() {

        List<SelectItem> itens = new ArrayList<SelectItem>();

        for (EstadoCivil ec : EstadoCivil.values()) {

            itens.add(new SelectItem(ec, ec.getDescricao(getLocale())));

        }

        return itens;

    }

    @Override
    protected void handleCreate(Usuario entity) {

        try {

            usuarioFacade.criaUsuarioParaPessoa(entity);
            resetBean();
            addFacesMessage("cadastroSucesso");

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CAB.inserir.Exception", e);

        }

    }

    @Override
    protected List<Usuario> handleRetrieve(String searchTerm) {

        try {

            return usuarioFacade.findByNomeAndTipoPerfil(searchTerm, perfilBusca);

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CAB.handleResultList.Exception", e);
            return null;

        }

    }

    @Override
    protected void handleUpdate(Usuario entity) {

        try {

            usuarioFacade.alterar(entity);
            addFacesMessage("alteradoSucesso");

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CUB.handleUpdate.Exception", e);

        }

    }

    @Override
    protected boolean handleDelete(Usuario entity) {

        try {

            usuarioFacade.remover(entity);
            addFacesMessage("remocaoSucesso");

            return true;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CAB.handleDelete.Exception", e);

            return false;

        }

    }

    @Override
    protected void handlePrepareToUpdate() {

    }

    private void resetBean() {

        setEntity(new Usuario(new Pessoa()));
        getEntity().setPerfil(new Perfil());

    }

    public Boolean getCpfReadOnly() {
        return cpfReadOnly;
    }

    public void setCpfReadOnly(Boolean cpfReadOnly) {
        this.cpfReadOnly = cpfReadOnly;
    }

    public Locale getDefaultUserLocale() {
        return defaultUserLocale;
    }

    public void setDefaultUserLocale(Locale defaultUserLocale) {
        this.defaultUserLocale = defaultUserLocale;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public TipoPerfil getPerfilBusca() {
        return perfilBusca;
    }

    public void setPerfilBusca(TipoPerfil perfilBusca) {
        this.perfilBusca = perfilBusca;
    }
    
}
