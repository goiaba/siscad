/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.exception.EnderecoRequeridoException;
import br.com.r2jb.siscad.business.exception.EscolaJaCadastradaException;
import br.com.r2jb.siscad.business.exception.TelefoneRequeridoException;
import br.com.r2jb.siscad.business.facade.escola.EscolaFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Endereco;
import br.com.r2jb.siscad.business.entity.EscolaCurso;
import br.com.r2jb.siscad.business.facade.curso.CursoFacade;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;

/**
 *
 * @author Ricardo
 */
@ManagedBean
@ViewScoped
public class CrudEscolaBean extends AbstractCrudBean<Escola> {

    @ManagedProperty(value="#{enderecoBean}") private EnderecoBean enderecoBean;
    @ManagedProperty(value="#{telefoneBean}") private TelefoneBean telefoneBean;

    private List<SelectItem> listaCursos = new ArrayList<SelectItem>();
    private Curso[] listaCursosSelecionados;

    transient private EscolaFacade escolaFacade = ServiceLocator.getFacade(EscolaFacade.class);
    transient private CursoFacade cursoFacade = ServiceLocator.getFacade(CursoFacade.class);

    @PostConstruct
    public void init(){

        resetBean();

        this.retrieve();

        List<SelectItem> itens = new ArrayList<SelectItem>();

        for (Curso curso : cursoFacade.findAll()) {

            itens.add(new SelectItem(curso, curso.getDescricao()));

        }

        setListaCursos(itens);
        
    }

    public void resetBean() {

        setEntity(new Escola());
        enderecoBean.resetBean();
        telefoneBean.resetBean();
        listaCursosSelecionados = null;

    }

    @Override
    protected void handleCreate(Escola entity) {

        List<Curso> cursos = new ArrayList<Curso>();

        try {

            //TODO: Essa validação faz sentido!?!?
            if (null != listaCursosSelecionados) {

                cursos = Arrays.asList(listaCursosSelecionados);

            }

            escolaFacade.inserir(entity, enderecoBean.getEnderecos(), telefoneBean.getTelefones(), cursos);
            resetBean();

            addFacesMessage("cadastroSucesso");

        } catch (EnderecoRequeridoException ere) {

            addFacesMessage("enderecoRequeridoExceptionMessage", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CEB.handleCreate.EnderecoRequeridoException", ere);

        } catch (TelefoneRequeridoException tre) {

            addFacesMessage("telefoneRequeridoExceptionMessage", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CEB.handleCreate.TelefoneRequeridoException", tre);

        } catch (EscolaJaCadastradaException ejce) {

            addFacesMessage("escolaJaCadastradaExceptionMessage", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CEB.handleCreate.EscolaJaCadastradaException", ejce);

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CEB.handleCreate.Exception", e);

        }

    }

    @Override
    protected List<Escola> handleRetrieve(String searchTerm) {

        try {

            return escolaFacade.findAll();

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CEB.handleRetrieve.Exception", e);
            return null;

        }

    }

    @Override
    protected void handleUpdate(Escola entity) {

        try {

            escolaFacade.alterar(entity, Arrays.asList(listaCursosSelecionados));
            addFacesMessage("alteradoSucesso");

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CEB.handleUpdate.Exception", e);

        }

    }

    @Override
    protected boolean handleDelete(Escola entity) {

        try {

            escolaFacade.remover(entity);
            addFacesMessage("remocaoSucesso");

            return true;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CEB.handleDelete.Exception", e);

            return false;

        }

    }

    @Override
    protected void handlePrepareToUpdate() {

        if (null != getEntity() && null != getEntity().getListaEscolaCurso() && !getEntity().getListaEscolaCurso().isEmpty()) {

            List<Curso> cursos = new ArrayList<Curso>();

            for (EscolaCurso ec : getEntity().getListaEscolaCurso()) {

                cursos.add(ec.getCurso());

            }

            listaCursosSelecionados = cursos.toArray(new Curso[]{});

        }

        List<Endereco> enderecos = new ArrayList<Endereco>();

        enderecos.add(getEntity().getEndereco());

        enderecoBean.addEnderecosEscola(enderecos);
        telefoneBean.addTelefonesEscola(getEntity().getListaEscolaTelefone());

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
     * @return the listaCursos
     */
    public List<SelectItem> getListaCursos() {
        return listaCursos;
    }

    /**
     * @param listaCursos the listaCursos to set
     */
    public void setListaCursos(List<SelectItem> listaCursos) {
        this.listaCursos = listaCursos;
    }

    /**
     * @return the listaCursosSelecionados
     */
    public Curso[] getListaCursosSelecionados() {
        return listaCursosSelecionados;
    }

    /**
     * @param listaCursosSelecionados the listaCursosSelecionados to set
     */
    public void setListaCursosSelecionados(Curso[] listacursosSelecionados) {
        this.listaCursosSelecionados = listacursosSelecionados;
    }
    
}