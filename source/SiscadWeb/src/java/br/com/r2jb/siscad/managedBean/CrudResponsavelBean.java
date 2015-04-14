/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.business.entity.Aluno;
import br.com.r2jb.siscad.business.entity.Pessoa;
import br.com.r2jb.siscad.business.entity.Responsavel;
import br.com.r2jb.siscad.business.exception.PessoaJaExisteException;
import br.com.r2jb.siscad.business.exception.ResponsavelPossuiDependentesException;
import br.com.r2jb.siscad.business.facade.responsavel.ResponsavelFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@ManagedBean
@ViewScoped
public class CrudResponsavelBean extends CrudPessoaBean {

    transient private ResponsavelFacade responsavelFacade = ServiceLocator.getFacade(ResponsavelFacade.class);

    private Responsavel responsavel;

    public CrudResponsavelBean() {

        resetBeanEspecifico();
        
    }

    @Override
    protected boolean abstractInserir() throws PessoaJaExisteException {

        return getResponsavelFacade().inserirResponsavel( getResponsavel(), enderecoBean.getEnderecos(), telefoneBean.getTelefones(), getLocale());

    }

    @Override
    public String alterar() {

        try {

            getResponsavelFacade().alterarResponsavel(getResponsavel());

            resetBeanEspecifico();
            addFacesMessage("alteracaoSucesso");
            setMode(RETRIEVE_MODE_PARAMETER);
            return null;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CRB.alterar.Exception", e);
            return null;

        }

    }

    @Override
    protected void remover() {

        try {

            if (getTipoFuncao() != null) {

                getResponsavelFacade().removerResponsavel((Responsavel) getTipoFuncao());
                atualizaListaPorTermoBusca();
                addFacesMessage("responsavelRemovido");

            } else {

                addFacesMessage("nenhumResponsavelSelecionado");

            }

        } catch (ResponsavelPossuiDependentesException e) {

            StringBuilder sDependentes  = new StringBuilder();

            for (Aluno a : e.getAlunos()) {

                sDependentes.append(Integer.toString(a.getRa()))
                            .append(" - ")
                            .append(a.getNome())
                            .append("<br/>");
                
            }

            addFacesMessage(null, "responsavelPossuiDependentesException", FacesMessage.SEVERITY_ERROR, sDependentes.toString());
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CRB.remover.ResponsavelPossuiDependentesException", e);

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CRB.remover.Exception", e);

        }
        
    }

    @Override
    public void buscar() {

        try {

            atualizaListaPorTermoBusca();

            if (getListaRetornoBusca().getRowCount() != 0) {

                setRenderTable(Boolean.TRUE);

            } else {

                setRenderTable(Boolean.FALSE);
                addFacesMessage("listaVazia");

            }

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CRB.buscar.Exception", e);

        }
        
    }

    @Override
    protected boolean verificaCpf(String cpf) {

        return getResponsavelFacade().responsavelExiste(cpf);
        
    }

    @Override
    public String preparaParaAlteracao() {

        resetBean();

        responsavel = (Responsavel) listaRetornoBusca.getRowData();

        enderecoBean.addEnderecos(responsavel.getPessoa().getListaEndereco());
        telefoneBean.addTelefones(responsavel.getPessoa().getListaTelefone());

        setCpfReadOnly(Boolean.TRUE);
        setMode(UPDATE_MODE_PARAMETER);

        return null;

    }

    @Override
    protected final void resetBeanEspecifico() {

        setResponsavel(new Responsavel());
        getResponsavel().setPessoa(new Pessoa());
        
    }

    private void atualizaListaPorTermoBusca() {

        try {

            setListaRetornoBusca(getResponsavelFacade().findByNome(termoBusca, Boolean.TRUE));

        } catch(Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CRB.atualizaListaPorTermoBusca.Exception", e);

        }

    }

    /**
     * @return the responsavelFacade
     */
    public ResponsavelFacade getResponsavelFacade() {
        return responsavelFacade;
    }

    /**
     * @param responsavelFacade the responsavelFacade to set
     */
    public void setResponsavelFacade(ResponsavelFacade responsavelFacade) {
        this.responsavelFacade = responsavelFacade;
    }

    /**
     * @return the responsavel
     */
    public Responsavel getResponsavel() {
        return responsavel;
    }

    /**
     * @param responsavel the responsavel to set
     */
    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

}
