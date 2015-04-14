/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Serie;
import br.com.r2jb.siscad.business.facade.curso.CursoFacade;
import br.com.r2jb.siscad.business.facade.serie.SerieFacade;
import com.rits.cloning.Cloner;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author Ricardo
 */
@ManagedBean
@ViewScoped
public class CrudSerieBean extends AbstractCrudBean<Serie> {

    transient private CursoFacade cursoFacade = ServiceLocator.getFacade(CursoFacade.class);
    transient private SerieFacade serieFacade = ServiceLocator.getFacade(SerieFacade.class);

    private List<Curso> listaCurso = new ArrayList<Curso>();
    private List<Serie> listaSeriePosterior = new ArrayList<Serie>();
    private List<Serie> listaSeriesPorCurso = new ArrayList<Serie>();

    private Curso curso = new Curso();

    /** Creates a new instance of CrudSerieBean */
    public CrudSerieBean() {
    }

    @PostConstruct
    public void init() {
        resetBean();
        this.setListaCurso(cursoFacade.findAll());
        this.setListaSeriePosterior(serieFacade.findAll());
    }

    public void resetBean() {
        setEntity(new Serie());
    }

    @Override
    protected void handleCreate(Serie entity) {
        try {

            serieFacade.inserir(entity);
            resetBean();
            addFacesMessage("cadastroSucesso");

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CSB.inserir.Exception", e);

        }
    }

    @Override
    protected List<Serie> handleRetrieve(String searchTerm) {

        try {

            List<Serie> series = new ArrayList<Serie>();
            series = serieFacade.findSeriePorCurso(curso);
            return series;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CSB.handleRetrieve.Exception", e);
            return null;

        }

    }

    @Override
    protected void handleUpdate(Serie entity) {

        try {

            serieFacade.alterar(entity);
            addFacesMessage("alteradoSucesso");

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CSB.handleUpdate.Exception", e);

        }

    }

    @Override
    protected boolean handleDelete(Serie entity) {

        try {

            serieFacade.remover(entity);
            addFacesMessage("remocaoSucesso");

            return true;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CSB.handleDelete.Exception", e);

            return false;

        }

    }

    @Override
    protected void handlePrepareToUpdate() {

    }

    /**
     * @return the listaCurso
     */
    public List<Curso> getListaCurso() {
        return listaCurso;
    }

    /**
     * @param listaCurso the listaCurso to set
     */
    public void setListaCurso(List<Curso> listaCurso) {
        this.listaCurso = listaCurso;
    }

    /**
     * @return the listaSeriePosterior
     */
    public List<Serie> getListaSeriePosterior() {
        return listaSeriePosterior;
    }

    /**
     * @param listaSeriePosterior the listaSeriePosterior to set
     */
    public void setListaSeriePosterior(List<Serie> listaSeriePosterior) {
        this.listaSeriePosterior = listaSeriePosterior;
    }

    /**
     * @return the curso
     */
    public Curso getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    /**
     * @return the listaSeriesPorCurso
     */
    public List<Serie> getListaSeriesCadastradas() {
        return listaSeriesPorCurso;
    }

    /**
     * @param listaSeriesPorCurso the listaSeriesPorCurso to set
     */
    public void setListaSeriesCadastradas(List<Serie> listaSeriesCadastradas) {
        this.listaSeriesPorCurso = listaSeriesCadastradas;
    }

}
