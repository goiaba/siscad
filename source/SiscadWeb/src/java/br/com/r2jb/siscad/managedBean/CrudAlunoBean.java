/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.business.entity.Aluno;
import br.com.r2jb.siscad.business.entity.Responsavel;
import br.com.r2jb.siscad.business.facade.aluno.AlunoFacade;
import br.com.r2jb.siscad.business.facade.responsavel.ResponsavelFacade;
import br.com.r2jb.siscad.business.util.EstadoCivil;
import br.com.r2jb.siscad.business.util.Sexo;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;
import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.business.entity.Pessoa;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@ManagedBean
@ViewScoped
public final class CrudAlunoBean extends AbstractCrudBean<Aluno> {

    transient private AlunoFacade alunoFacade = ServiceLocator.getFacade(AlunoFacade.class);
    transient private ResponsavelFacade responsavelFacade = ServiceLocator.getFacade(ResponsavelFacade.class);

    @ManagedProperty(value="#{enderecoBean}") private EnderecoBean enderecoBean;
    @ManagedProperty(value="#{telefoneBean}") private TelefoneBean telefoneBean;

    private Responsavel responsavel;
    private Locale defaultUserLocale = getDefaultLocale();

    private Boolean mostraCampoRA = Boolean.FALSE;
    private Boolean camposResponsavelReadOnly = Boolean.FALSE;
    private Boolean camposResponsavelVisible = Boolean.TRUE;

    @PostConstruct
    public void postConstruct() {

        resetBean();

    }

    public void verifyCpfExists() {

        try {
            
            Responsavel innerResponsavel = responsavelFacade.findByCPF(getResponsavel().getPessoa().getCpf());

            if (null == innerResponsavel) {

                String cpf = getResponsavel().getPessoa().getCpf();

                resetResponsavel();
                setCamposResponsavelReadOnly(Boolean.FALSE);

                getResponsavel().getPessoa().setCpf(cpf);
                
            } else {

                setResponsavel(innerResponsavel);
                
                getEnderecoBean().resetBean();
                getTelefoneBean().resetBean();

                getEnderecoBean().addEnderecos(getResponsavel().getPessoa().getListaEndereco());
                getTelefoneBean().addTelefones(getResponsavel().getPessoa().getListaTelefone());

                setCamposResponsavelReadOnly(Boolean.TRUE);

            }

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CAB.validarCpf.Exception", e);

        }

    }

    public List<SelectItem> getListaEstadoCivil() {

        List<SelectItem> itens = new ArrayList<SelectItem>();

        for(EstadoCivil ec : EstadoCivil.values()) {

            itens.add(new SelectItem(ec, ec.getDescricao(getLocale())));

        }

        return itens;

    }

    public List<SelectItem> getListaSexo() {

        List<SelectItem> itens = new ArrayList<SelectItem>();

        for(Sexo s : Sexo.values()) {

            itens.add(new SelectItem(s, s.getDescricao(getLocale())));

        }

        return itens;

    }

    @Override
    protected List<Aluno> handleRetrieve(String searchTerm) {

        try {

            return alunoFacade.findByNome(searchTerm, Boolean.TRUE);

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CAB.handleResultList.Exception", e);
            return null;

        }
        
    }

    @Override
    protected void handleCreate(Aluno entity) {

        if (getEnderecoBean().verifyAtLeastAnAddressInformed() && getTelefoneBean().verifyAtLeastATelephoneInformed()) {

            try {

                if (responsavelFacade.responsavelExiste(getResponsavel().getPessoa().getCpf())) {

                    entity.setResponsavel(getResponsavel());
                    alunoFacade.inserirAluno(entity, defaultUserLocale);

                } else {

                    alunoFacade.inserirAlunoEResponsavel(entity, getResponsavel(), getEnderecoBean().getEnderecos(), getTelefoneBean().getTelefones(), defaultUserLocale);

                }

                resetBean();
                addFacesMessage("cadastroSucesso");

            } catch (Exception e) {

                addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CAB.handleCreate.Exception", e);

            }

        }

    }

    @Override
    protected void handleUpdate(Aluno entity) {

        try {

            alunoFacade.alterarAluno(entity);
            addFacesMessage("alteradoSucesso");

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CAB.handleUpdate.Exception", e);

        }

    }

    @Override
    protected boolean handleDelete(Aluno entity) {

        try {

            alunoFacade.removerAluno(entity);
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
        setCamposResponsavelVisible(Boolean.FALSE);
    }

    private void resetResponsavel() {

        setResponsavel(new Responsavel());
        getResponsavel().setPessoa(new Pessoa());

    }

    private void resetBean() {

        resetResponsavel();

        setEntity(new Aluno());

        getEnderecoBean().resetBean();
        getTelefoneBean().resetBean();

    }

    //---------------------------------------------------------Getters & Setters

    public Boolean getMostraCampoRA() {
        return mostraCampoRA;
    }

    public void setMostraCampoRA(Boolean mostraCampoRA) {
        this.mostraCampoRA = mostraCampoRA;
    }

    public Boolean getCamposResponsavelReadOnly() {
        return camposResponsavelReadOnly;
    }

    public void setCamposResponsavelReadOnly(Boolean camposResponsavelReadOnly) {
        this.camposResponsavelReadOnly = camposResponsavelReadOnly;
    }

    public Boolean getCamposResponsavelVisible() {
        return camposResponsavelVisible;
    }

    public void setCamposResponsavelVisible(Boolean camposResponsavelVisible) {
        this.camposResponsavelVisible = camposResponsavelVisible;
    }

    public Locale getDefaultUserLocale() {
        return defaultUserLocale;
    }

    public void setDefaultUserLocale(Locale defaultUserLocale) {
        this.defaultUserLocale = defaultUserLocale;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public EnderecoBean getEnderecoBean() {
        return enderecoBean;
    }

    public void setEnderecoBean(EnderecoBean enderecoBean) {
        this.enderecoBean = enderecoBean;
    }

    public TelefoneBean getTelefoneBean() {
        return telefoneBean;
    }

    public void setTelefoneBean(TelefoneBean telefoneBean) {
        this.telefoneBean = telefoneBean;
    }

}
