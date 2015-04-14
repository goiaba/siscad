/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.business.entity.TipoFuncao;
import br.com.r2jb.siscad.business.exception.PessoaJaExisteException;
import br.com.r2jb.siscad.business.util.EstadoCivil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public abstract class CrudPessoaBean extends BaseBean {

    @ManagedProperty(value = "#{enderecoBean}")
    protected EnderecoBean enderecoBean;

    @ManagedProperty(value = "#{telefoneBean}")
    protected TelefoneBean telefoneBean;

    protected Locale localePadrao = getDefaultLocale();

    protected String termoBusca = "";

    protected Boolean renderTable = Boolean.FALSE;
    protected Boolean cpfReadOnly = Boolean.FALSE;

    protected TipoFuncao tipoFuncao = new TipoFuncao();
    protected transient ListDataModel<? extends TipoFuncao> listaRetornoBusca;

    private String mode;

    protected abstract boolean abstractInserir() throws PessoaJaExisteException;

    public abstract String alterar();

    protected abstract void remover();

    public abstract void buscar();

    protected abstract boolean verificaCpf(String cpf);

    public abstract String preparaParaAlteracao();

    protected abstract void resetBeanEspecifico();
    
    public void validarCpf(FacesContext context, UIComponent toValidate, Object value) {

        try {

            String cpf = (String) ((UIInput) toValidate).getSubmittedValue();

            if (verificaCpf(cpf)) {

                ((UIInput) toValidate).setValid(false);

                addFacesMessage(toValidate.getClientId(context), "cpfExistente", FacesMessage.SEVERITY_ERROR);

            } else {

                ((UIInput) toValidate).setValid(true);

            }

        } catch (Exception e) {

            ((UIInput) toValidate).setValid(false);
            addFacesMessage(((UIInput) toValidate).getClientId(), "erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CPB.validarCpf.Exception", e);

        }

    }

    public String inserir() {

        if (!enderecoBean.verifyAtLeastAnAddressInformed() || !telefoneBean.verifyAtLeastATelephoneInformed()) {

            return null;

        }

        try {

            boolean bResult = abstractInserir();

            if (bResult) {

                resetBean();
                addFacesMessage("cadastroSucesso");
                return null;

            } else {

                addFacesMessage("cadastroProblema", FacesMessage.SEVERITY_ERROR);
                return null;

            }


        } catch (PessoaJaExisteException e) {

            addFacesMessage("pessoaJaExiste", FacesMessage.SEVERITY_ERROR, e.getTipoPessoa(), e.getCpf());
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CPB.addProfessor.PessoaJaExisteException", e);
            return null;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CPB.addProfessor.Exception", e);
            return null;

        }

    }

    public void removerListener() {
        
        remover();
        
    }

    public List<SelectItem> getListaEstadoCivil() {

        List<SelectItem> itens = new ArrayList<SelectItem>();

        for (EstadoCivil ec : EstadoCivil.values()) {

            itens.add(new SelectItem(ec, ec.getDescricao(getLocale())));

        }

        return itens;

    }

    public String voltar() {

        setMode(RETRIEVE_MODE_PARAMETER);
        return null;

    }

    protected void resetBean() {

        resetBeanEspecifico();

        enderecoBean.resetBean();
        telefoneBean.resetBean();
        
        cpfReadOnly = Boolean.FALSE;

    }

    /**
     * @return the enderecoBean
     */
    public EnderecoBean getEnderecoBean() {
        return enderecoBean;
    }

    /**
     * @param enderecoBean the enderecoBean to set
     */
    public void setEnderecoBean(EnderecoBean enderecoBean) {
        this.enderecoBean = enderecoBean;
    }

    /**
     * @return the telefoneBean
     */
    public TelefoneBean getTelefoneBean() {
        return telefoneBean;
    }

    /**
     * @param telefoneBean the telefoneBean to set
     */
    public void setTelefoneBean(TelefoneBean telefoneBean) {
        this.telefoneBean = telefoneBean;
    }

    /**
     * @return the localePadrao
     */
    public Locale getLocalePadrao() {
        return localePadrao;
    }

    /**
     * @param localePadrao the localePadrao to set
     */
    public void setLocalePadrao(Locale localePadrao) {
        this.localePadrao = localePadrao;
    }

    /**
     * @return the termoBusca
     */
    public String getTermoBusca() {
        return termoBusca;
    }

    /**
     * @param termoBusca the termoBusca to set
     */
    public void setTermoBusca(String termoBusca) {
        this.termoBusca = termoBusca;
    }

    /**
     * @return the renderTable
     */
    public Boolean getRenderTable() {
        return renderTable;
    }

    /**
     * @param renderTable the renderTable to set
     */
    public void setRenderTable(Boolean renderTable) {
        this.renderTable = renderTable;
    }

    /**
     * @return the cpfReadOnly
     */
    public Boolean getCpfReadOnly() {
        return cpfReadOnly;
    }

    /**
     * @param cpfReadOnly the cpfReadOnly to set
     */
    public void setCpfReadOnly(Boolean cpfReadOnly) {
        this.cpfReadOnly = cpfReadOnly;
    }

    /**
     * @return the tipoFuncao
     */
    public TipoFuncao getTipoFuncao() {
        return tipoFuncao;
    }

    /**
     * @param tipoFuncao the tipoFuncao to set
     */
    public void setTipoFuncao(TipoFuncao tipoFuncao) {
        this.tipoFuncao = tipoFuncao;
    }

    /**
     * @return the listaRetornoBusca
     */
    public ListDataModel<? extends TipoFuncao> getListaRetornoBusca() {
        return listaRetornoBusca;
    }

    /**
     * @param listaRetornoBusca the listaRetornoBusca to set
     */
    public void setListaRetornoBusca(List<? extends TipoFuncao> tipoFuncao) {

        listaRetornoBusca = new ListDataModel(tipoFuncao);

    }

    /**
     * @return the mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(String mode) {
        this.mode = mode;
    }
}
