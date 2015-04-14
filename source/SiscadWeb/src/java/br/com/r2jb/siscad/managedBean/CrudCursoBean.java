/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.facade.curso.CursoFacade;
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
public final class CrudCursoBean extends AbstractCrudBean<Curso> {

    transient private CursoFacade cursoFacade = ServiceLocator.getFacade(CursoFacade.class);

    @PostConstruct
    public void init() {

        resetBean();

        // TODO: só executar o metodo retrieve quando o mode for retrieve

//        if (getRetrieveMode()) {
        
            this.retrieve();

//        }
        
    }

    public void resetBean() {

        setEntity(new Curso());

    }

    @Override
    protected void handleCreate(Curso entity) {

        try {

            cursoFacade.inserir(entity);
            resetBean();
            addFacesMessage("cadastroSucesso");

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CCB.inserir.Exception", e);

        }

    }

    @Override
    protected List<Curso> handleRetrieve(String searchTerm) {

        try {

            return cursoFacade.findAll();

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CCB.handleRetrieve.Exception", e);
            return null;

        }

    }

    @Override
    protected void handleUpdate(Curso entity) {

        try {

            cursoFacade.alterar(entity);
            addFacesMessage("alteradoSucesso");

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CCB.handleUpdate.Exception", e);

        }

    }

    @Override
    protected boolean handleDelete(Curso entity) {

        try {

            cursoFacade.remover(entity);
            addFacesMessage("remocaoSucesso");

            return true;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CCB.handleDelete.Exception", e);

            return false;

        }

    }

    @Override
    protected void handlePrepareToUpdate() {
    }

}
