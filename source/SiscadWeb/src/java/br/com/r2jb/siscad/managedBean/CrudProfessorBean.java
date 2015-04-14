/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.business.entity.Pessoa;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.exception.PessoaJaExisteException;
import br.com.r2jb.siscad.business.facade.professor.ProfessorFacade;
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
public class CrudProfessorBean extends CrudPessoaBean {

    transient private ProfessorFacade professorFacade = ServiceLocator.getFacade(ProfessorFacade.class);

    private Professor professor;
    private Boolean renderMatricula = Boolean.FALSE;

    public CrudProfessorBean() {

        resetBeanEspecifico();
        
    }

    @Override
    protected boolean abstractInserir() throws PessoaJaExisteException {

        return getProfessorFacade().inserirProfessor( getProfessor(), enderecoBean.getEnderecos(), telefoneBean.getTelefones(), getLocale());

    }

    @Override
    public String alterar() {

        try {

            professorFacade.alterarProfessor(professor);

            resetBeanEspecifico();
            addFacesMessage("alteracaoSucesso");
            setMode(RETRIEVE_MODE_PARAMETER);
            return null;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CPB.alterar.Exception", e);
            return null;

        }

    }

    @Override
    protected void remover() {

        try {

            if (getTipoFuncao() != null) {
                
                professorFacade.removerProfessor((Professor) getTipoFuncao());
                atualizaListaPorTermoBusca();
                addFacesMessage("professorRemovido");

            } else {

                addFacesMessage("nenhumProfessorSelecionado");

            }

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CPB.remover.Exception", e);

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
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CPB.buscar.Exception", e);

        }

    }

    @Override
    protected boolean verificaCpf(String cpf) {

        return getProfessorFacade().professorExiste(cpf);

    }

    @Override
    public String preparaParaAlteracao() {

        resetBean();

        professor = (Professor) listaRetornoBusca.getRowData();

        enderecoBean.addEnderecos(professor.getPessoa().getListaEndereco());
        telefoneBean.addTelefones(professor.getPessoa().getListaTelefone());

        setCpfReadOnly(Boolean.TRUE);
        setMode(UPDATE_MODE_PARAMETER);

        return null;

    }

    @Override
    protected final void resetBeanEspecifico() {

        setProfessor(new Professor());
        getProfessor().setPessoa(new Pessoa());

        setRenderMatricula(Boolean.FALSE);
        
    }

    private void atualizaListaPorTermoBusca() {

        try {

            setListaRetornoBusca(professorFacade.findByNome(termoBusca, Boolean.TRUE));

        } catch(Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CPB.atualizaListaPorTermoBusca.Exception", e);

        }


    }

    /**
     * @return the professorFacade
     */
    public ProfessorFacade getProfessorFacade() {
        return professorFacade;
    }

    /**
     * @param professorFacade the professorFacade to set
     */
    public void setProfessorFacade(ProfessorFacade professorFacade) {
        this.professorFacade = professorFacade;
    }

    /**
     * @return the professor
     */
    public Professor getProfessor() {
        return professor;
    }

    /**
     * @param professor the professor to set
     */
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    /**
     * @return the renderMatricula
     */
    public Boolean getRenderMatricula() {
        return renderMatricula;
    }

    /**
     * @param renderMatricula the renderMatricula to set
     */
    public void setRenderMatricula(Boolean renderMatricula) {
        this.renderMatricula = renderMatricula;
    }

}
